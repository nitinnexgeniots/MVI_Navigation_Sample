package com.nitin.uimodule.settings

import android.os.Parcelable
import androidx.compose.runtime.Immutable
import com.nitin.network.GetContinentsQuery
import com.nitin.network.data.remote.model.response.QuoteList
import kotlinx.android.parcel.Parcelize
import kotlinx.parcelize.IgnoredOnParcel



@Immutable
@Parcelize
data class SettingsUiState(
    @IgnoredOnParcel val isLoading: Boolean = false,
    val isError: Boolean = false,
    @IgnoredOnParcel var alertsDataResponse:  List<GetContinentsQuery.Continent>? = null,
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

        data class GraphQlData(val response: List<GetContinentsQuery.Continent>) :PartialState()


    }
}