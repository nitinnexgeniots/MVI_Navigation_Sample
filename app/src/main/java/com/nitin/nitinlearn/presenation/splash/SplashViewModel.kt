package com.nitin.nitinlearn.presenation.splash

import androidx.lifecycle.SavedStateHandle
import com.nitin.core.base.BaseViewModel
import com.nitin.core.navigation.NavigationManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    val navigationManager: NavigationManager,
    savedStateHandle: SavedStateHandle,
    splashUiState: SplashUiState,
) : BaseViewModel<SplashUiState, SplashUiState.PartialState, SplashEvent, SplashIntent>(
    savedStateHandle,
    splashUiState
)
{
    override fun mapIntents(intent: SplashIntent): Flow<SplashUiState.PartialState> {
        TODO("Not yet implemented")
    }

    override fun reduceUiState(
        previousState: SplashUiState,
        partialState: SplashUiState.PartialState
    ): SplashUiState {
        TODO("Not yet implemented")
    }

}