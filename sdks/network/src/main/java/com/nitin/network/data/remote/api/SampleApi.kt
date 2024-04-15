package com.nitin.network.data.remote.api

import androidx.annotation.Keep
import com.nitin.network.data.remote.model.response.QuoteList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

@Keep
interface SampleApi {
    @GET
    suspend fun getQuotes(@Url url: String) : Response<QuoteList?>
}