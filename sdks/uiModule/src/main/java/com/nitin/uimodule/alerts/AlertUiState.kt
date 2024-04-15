package com.nitin.uimodule.alerts

import android.os.Parcelable
import androidx.compose.runtime.Immutable
import com.nitin.network.data.remote.model.response.QuoteList
import kotlinx.android.parcel.Parcelize
import kotlinx.parcelize.IgnoredOnParcel


@Immutable
@Parcelize
data class AlertUiState(
    @IgnoredOnParcel val isLoading: Boolean = false,
    val isError: Boolean = false,
    @IgnoredOnParcel var alertsDataResponse: QuoteList? = null,
    ) : Parcelable {


    /**
     * Partial state
     *
     * @constructor Create empty Partial state
     */
    sealed class PartialState {
        data class Loading(val isLoading: Boolean) : PartialState()
        /**
         * Error
         *
         * @property throwable
         * @constructor Create empty Error
         */
        data class Error(val isError: Boolean) : PartialState()

        data class AlertsData(val response:QuoteList?) :PartialState()


    }
}
