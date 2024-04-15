package com.nitin.network.data.remote.model

import androidx.annotation.Keep
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Keep
@Serializable
data class ErrorResponse(
    @SerialName("code") val code: String? = null
)
data class RestApiCallException(
    val errorCode: Int?,
    var errorMessage: String?,
    val errorResponse: ErrorResponse? = null,
    val errorCause: Throwable?
) :
    Throwable(errorMessage, errorCause)
