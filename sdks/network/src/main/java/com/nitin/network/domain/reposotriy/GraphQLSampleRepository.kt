package com.nitin.network.domain.reposotriy

import com.nitin.network.GetContinentsQuery
import com.nitin.network.type.Continent
import kotlinx.coroutines.flow.Flow

interface GraphQLSampleRepository {
    suspend fun getSampleResponse(): Flow<List<GetContinentsQuery.Continent>>
}