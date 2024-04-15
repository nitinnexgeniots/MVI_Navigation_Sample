package com.nitin.network.domain.usecases

import com.nitin.core.extensions.resultOf
import com.nitin.network.GetContinentsQuery
import com.nitin.network.domain.reposotriy.GraphQLSampleRepository
import com.nitin.network.type.Continent
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.retryWhen
import java.io.IOException

private const val RETRY_TIME_IN_MILLIS = 3_000L

fun interface GetGraphqlSampleUseCase : suspend () -> Flow<Result<List<GetContinentsQuery.Continent>>>
suspend fun getSampleResponse(
    graphqlRepository: GraphQLSampleRepository
): Flow<Result<List<GetContinentsQuery.Continent>>> = graphqlRepository
    .getSampleResponse()
    .map {
        resultOf { it }
    }.retryWhen { cause, _ ->
        if (cause is IOException) {
            emit(Result.failure(cause))
            delay(RETRY_TIME_IN_MILLIS)
            true
        } else {
            false
        }
    }
    .catch {
        emit(Result.failure(it))
    }
