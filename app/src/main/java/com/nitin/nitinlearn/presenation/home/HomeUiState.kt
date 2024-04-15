package com.nitin.nitinlearn.presenation.home

import android.os.Parcelable
import androidx.compose.runtime.Immutable
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize
import javax.inject.Inject



@Immutable
@Parcelize
data class HomeUiState @Inject constructor(
    val isLoading: Boolean ,
    val isError: Boolean,
) : Parcelable {


    /**
     * Partial state
     *
     * @constructor Create empty Partial state
     */
    sealed class PartialState {
        object Loading : PartialState() // for simplicity: initial loading & refreshing



        /**
         * Error
         *
         * @property throwable
         * @constructor Create empty Error
         */
        data class Error(val throwable: Throwable) : PartialState()
    }
}