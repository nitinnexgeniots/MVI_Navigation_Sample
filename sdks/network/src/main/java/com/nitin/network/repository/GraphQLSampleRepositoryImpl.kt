package com.nitin.network.repository

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.api.Optional
import com.apollographql.apollo3.cache.normalized.FetchPolicy
import com.apollographql.apollo3.cache.normalized.apolloStore
import com.apollographql.apollo3.cache.normalized.fetchPolicy
import com.nitin.network.GetContinentsQuery
import com.nitin.network.domain.reposotriy.GraphQLSampleRepository
import com.nitin.network.type.Continent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import timber.log.Timber
import javax.inject.Inject

class GraphQLSampleRepositoryImpl @Inject constructor(
private val apolloClient : ApolloClient
) : GraphQLSampleRepository
{

    private var continetQuery = GetContinentsQuery()



    override suspend fun getSampleResponse(): Flow<List<GetContinentsQuery.Continent>> {
        val isCacheCleared = apolloClient.apolloStore.clearAll()
      //  val newClient = apolloClient.newBuilder().autoPersistedQueries().autoPersistedQueries().serverUrl("https://countries.trevorblades.com").build()
          val newClient = apolloClient.newBuilder().serverUrl("https://countries.trevorblades.com").build()
        return newClient.query(continetQuery).fetchPolicy(FetchPolicy.CacheAndNetwork).sendApqExtensions(true).toFlow()
            .map { response -> response.dataAssertNoErrors.continents }
    }





}