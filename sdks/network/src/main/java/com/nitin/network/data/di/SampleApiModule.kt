package com.nitin.network.data.di

import com.nitin.network.data.remote.api.SampleApi
import com.nitin.network.domain.reposotriy.SampleRepository
import com.nitin.network.domain.usecases.GetSampleApiQuotesDataUseCase
import com.nitin.network.domain.usecases.getQuotes
import com.nitin.network.repository.SampleRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SampleApiModule {

    @Provides
    @Singleton
    fun provideSampleApi(
        retrofit: Retrofit
    ): SampleApi {
        return retrofit.create(SampleApi::class.java)
    }

    @Provides
    @Singleton
    fun provideSampleApiRepository(
        sampleApi: SampleApi
    ): SampleRepository {
        return SampleRepositoryImpl(sampleApi)
    }

    /**
     * Provide TeamChampionShip status usecase
     *
     * @param teamChampionShipRepository
     * @return
     */

    @Provides
    fun provideGetSampleApiQuotesDataUseCase(
        sampleRepository: SampleRepository
    ): GetSampleApiQuotesDataUseCase {
        return GetSampleApiQuotesDataUseCase { getQuotes(it,sampleRepository) }
    }


}