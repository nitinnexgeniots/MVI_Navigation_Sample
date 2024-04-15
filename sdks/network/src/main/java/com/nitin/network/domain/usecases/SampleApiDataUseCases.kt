package com.nitin.network.domain.usecases

import com.nitin.network.data.remote.model.response.QuoteList
import com.nitin.network.domain.reposotriy.SampleRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.catch
import resultOf

fun interface GetSampleApiQuotesDataUseCase : (String) -> Flow<Result<QuoteList?>>


fun getQuotes(
    url: String,
    sampleRepository: SampleRepository
): Flow<Result<QuoteList?>> = sampleRepository
    .getQuotes(url)
    .map {
        resultOf { it }
    }.catch {
        emit(Result.failure(it))
    }