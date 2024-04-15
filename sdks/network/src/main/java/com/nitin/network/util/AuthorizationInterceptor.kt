package com.nitin.network.util


import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.Interceptor
import okhttp3.Response
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Provider

//class AuthorizationInterceptor @Inject constructor(
//    @ApplicationContext val context: Context,
//    val appDataRepository: AppDataRepository,
//    private val refreshTokenManager: RefreshTokenManager
//) : Interceptor {
//
//    override fun intercept(chain: Interceptor.Chain): Response {
//        val myTrace = Firebase.performance.newTrace("appcms_network_trace")
//        myTrace.start()
//        Timber.d(chain.request().url.toString() + " : " +chain.request().header("X-APOLLO-OPERATION-NAME").toString())
//        val operationName = chain.request().header("X-APOLLO-OPERATION-NAME")
//        val token = if (!operationName.equals("BootStrapQuery", true)) {
//            refreshTokenManager.getAuth().orEmpty()
//        } else ""
//
//        val request = chain.request().newBuilder()
//            .addHeader("contentType", "application/json")
//            .addHeader("x-api-key", BuildConfig.X_API_KEY)
//
//        if (token.isNotBlank()) {
//            request.addHeader("Authorization", token)
//        }
//        myTrace.stop()
//        return chain.proceed(request.build())
//    }
//}