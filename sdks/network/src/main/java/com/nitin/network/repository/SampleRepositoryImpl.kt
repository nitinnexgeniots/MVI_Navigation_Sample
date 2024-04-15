package com.nitin.network.repository

import com.nitin.network.data.remote.api.SampleApi
import com.nitin.network.domain.reposotriy.SampleRepository
import kotlinx.coroutines.flow.flow

class SampleRepositoryImpl(
    private val sampleApi: SampleApi
) : SampleRepository {
    override fun getQuotes(url: String) = flow {
        val response = sampleApi.getQuotes(
            url = url,
        )
        emit(response.body())

    }
}

