package com.nitin.nitinlearn.presenation.splash

import android.os.Parcelable
import androidx.compose.runtime.Immutable
import kotlinx.android.parcel.IgnoredOnParcel
import kotlinx.android.parcel.Parcelize
import javax.inject.Inject



@Immutable
@Parcelize
data class SplashUiState @Inject constructor(
    val isLoading: Boolean = false,
    @IgnoredOnParcel val isForceUpdate: Pair<Boolean, Boolean>?,
    val isError: Boolean = false,
    @IgnoredOnParcel val error: Throwable? = null,
) : Parcelable {


    /**
     * Partial state
     *
     * @constructor Create empty Partial state
     */
    sealed class PartialState {
        object Loading : PartialState() // for simplicity: initial loading & refreshing



        data class ForceUpdateAvailable(val isForceUpdate: Pair<Boolean, Boolean>) : PartialState()

        /**
         * Error
         *
         * @property throwable
         * @constructor Create empty Error
         */
        data class Error(val throwable: Throwable) : PartialState()

    }
}
