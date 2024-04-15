package com.nitin.network.domain.reposotriy

import com.nitin.network.data.remote.model.response.QuoteList
import kotlinx.coroutines.flow.Flow

interface SampleRepository {
    fun getQuotes(url: String): Flow<QuoteList?>

}