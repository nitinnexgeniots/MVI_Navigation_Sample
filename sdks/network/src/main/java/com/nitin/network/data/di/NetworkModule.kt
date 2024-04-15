package com.nitin.network.data.di
import android.content.Context
import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.cache.normalized.api.MemoryCacheFactory
import com.apollographql.apollo3.cache.normalized.normalizedCache
import com.apollographql.apollo3.cache.normalized.sql.BuildConfig
import com.apollographql.apollo3.cache.normalized.sql.SqlNormalizedCacheFactory
import com.apollographql.apollo3.network.okHttpClient
//import com.apollographql.apollo3.ApolloClient
//import com.apollographql.apollo3.cache.normalized.api.MemoryCacheFactory
//import com.apollographql.apollo3.cache.normalized.normalizedCache
//import com.apollographql.apollo3.cache.normalized.sql.BuildConfig
//import com.apollographql.apollo3.cache.normalized.sql.SqlNormalizedCacheFactory
//import com.apollographql.apollo3.network.okHttpClient
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.moczul.ok2curl.CurlInterceptor
import com.moczul.ok2curl.logger.Logger
import com.nitin.network.data.remote.api.SampleApi
import com.nitin.network.util.AnnotatedConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import retrofit2.converter.simplexml.SimpleXmlConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val INTERCEPTOR_LOGGING_NAME = "INTERCEPTOR_LOGGING"
    private const val INTERCEPTOR_LOGGING_CURL = "INTERCEPTOR_LOGGING_CURL"
    private const val TIMEOUT = 180L // IN_SECONDS


    @Provides
    @Named(INTERCEPTOR_LOGGING_NAME)
    fun provideHttpLoggingInterceptor(): Interceptor {
        return if (BuildConfig.DEBUG) {
          return  HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
        } else {
            noOpInterceptor()
        }
    }

    @Provides
    @Named(INTERCEPTOR_LOGGING_CURL)
    fun provideCurlLoggingInterceptor(): Interceptor {
        return if (BuildConfig.DEBUG) {
           return CurlInterceptor(object : Logger {
                override fun log(message: String) {
                    Timber.d("Ok2Curl -> $message")
                }
            })
        } else {
            noOpInterceptor()
        }
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        @Named(INTERCEPTOR_LOGGING_NAME) loggingInterceptor: Interceptor,
     //   authorizationInterceptor: AuthorizationInterceptor,
        @Named(INTERCEPTOR_LOGGING_CURL) curlInterceptor: Interceptor
    ): OkHttpClient {
        return OkHttpClient
            .Builder()
            .apply {
                addNetworkInterceptor(loggingInterceptor)
           //     addInterceptor(authorizationInterceptor)
                addInterceptor(curlInterceptor)
                connectTimeout(TIMEOUT, TimeUnit.SECONDS)
                writeTimeout(TIMEOUT, TimeUnit.SECONDS)
                readTimeout(TIMEOUT, TimeUnit.SECONDS)
                if(BuildConfig.DEBUG) {
                    this.hostnameVerifier { _, _ -> true }
                }
            }
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient
    ): Retrofit {
        val gson: Gson = GsonBuilder()
            .setLenient()
            .create()

        return Retrofit
            .Builder()
            .addConverterFactory(
                AnnotatedConverterFactory.Builder()
                    .add(AnnotatedConverterFactory.Xml::class.java, SimpleXmlConverterFactory.createNonStrict())
                    .add(AnnotatedConverterFactory.Json::class.java, GsonConverterFactory.create(gson))
                    .build())
       // BuildConfig.BASE_URL
            .baseUrl("https://quotable.io"+"/")
            .client(okHttpClient)
            .build()
    }

    private fun noOpInterceptor(): Interceptor {
        return Interceptor { chain ->
            chain.proceed(chain.request())
        }
    }



    @Provides
    @Singleton
    fun provideApolloClient(@ApplicationContext context: Context, okHttpClient: OkHttpClient): ApolloClient {
        val sqlCacheFactory = SqlNormalizedCacheFactory(context, "APOLLO_CLIENT_DB");
        val memoryFirstThenSqlCacheFactory = MemoryCacheFactory(maxSizeBytes = 10 * 1024 * 1024).chain(sqlCacheFactory)
        return ApolloClient.Builder()
      //  BuildConfig.BASE_URL
            .serverUrl("https://countries.trevorblades.com")
            .autoPersistedQueries()
            .okHttpClient(okHttpClient)
            .normalizedCache(memoryFirstThenSqlCacheFactory, writeToCacheAsynchronously = true)
            .build()
    }
   }
