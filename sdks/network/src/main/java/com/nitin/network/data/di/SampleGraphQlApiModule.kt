package com.nitin.network.data.di

import com.nitin.core.navigation.NavigationFactory
import com.nitin.network.domain.reposotriy.GraphQLSampleRepository
import com.nitin.network.domain.usecases.GetGraphqlSampleUseCase
import com.nitin.network.domain.usecases.getSampleResponse
import com.nitin.network.repository.GraphQLSampleRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet
import javax.inject.Singleton


@Module(includes = [SampleGraphQlApiModule.BindsModule::class])
@InstallIn(SingletonComponent::class)
class SampleGraphQlApiModule {
    @Provides
    fun provideGetGraphqlSampleUseCase(
        graphQlRepository: GraphQLSampleRepository
    ): GetGraphqlSampleUseCase {
        return GetGraphqlSampleUseCase {
            getSampleResponse(graphQlRepository)
        }
    }

    @Module
    @InstallIn(SingletonComponent::class)
    interface BindsModule {

        /**
         * Bind bootstrap repository
         *
         * @param impl
         * @return
         */
        /* @Binds
         @Singleton
         fun bindBootstrapRepository(impl: BootstrapRepositoryImpl): BootstrapRepository*/
    }


    @Module
    @InstallIn(SingletonComponent::class)
    interface SplashSingletonModule {



        /**
         * Bind bootstrap repository
         *
         * @param impl
         * @return
         */
        @Binds
        @Singleton
        fun bindBootstrapRepository(impl: GraphQLSampleRepositoryImpl): GraphQLSampleRepository
    }
}