package com.nitin.uimodule.settings

import androidx.lifecycle.SavedStateHandle
import com.nitin.core.base.BaseViewModel
import com.nitin.network.domain.usecases.GetGraphqlSampleUseCase
import com.nitin.network.domain.usecases.GetSampleApiQuotesDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    settingsInitialState: SettingsUiState,
    private val getGraphqlSampleUseCase: GetGraphqlSampleUseCase,
    val sampleApiQuotesDataUseCase: GetSampleApiQuotesDataUseCase,
) : BaseViewModel<SettingsUiState, SettingsUiState.PartialState, SettingsDataIntent, SettingsDataIntent>(
    savedStateHandle,
    settingsInitialState
) {
    override fun mapIntents(intent: SettingsDataIntent): Flow<SettingsUiState.PartialState> =
        when (intent) {
            is SettingsDataIntent.GetAlertsData -> getGraphQlData()
        }


    override fun reduceUiState(
        previousState: SettingsUiState,
        partialState: SettingsUiState.PartialState
    ): SettingsUiState = when (partialState) {

        is SettingsUiState.PartialState.Loading -> previousState.copy(
            isError = false,
            isLoading = partialState.isLoading,
        )
        is SettingsUiState.PartialState.Error -> previousState.copy(
            isError = partialState.isError,
        )
        is SettingsUiState.PartialState.GraphQlData -> previousState.copy(
            isError = false,
            isLoading = false,
            alertsDataResponse = partialState.response

        )



    }

    private fun getGraphQlData(): Flow<SettingsUiState.PartialState> = flow {
        getGraphqlSampleUseCase()
            .onStart {
                emit(SettingsUiState.PartialState.Loading(true))
                Timber.e("Nitin onStart")
            }
            .collect { result ->
                result
                    .onSuccess { response ->
                        Timber.e("Nitin onSuccess")
                         emit(SettingsUiState.PartialState.GraphQlData(response))
                    }
                    .onFailure {
                        Timber.e("Nitin onFailure")

                          emit(SettingsUiState.PartialState.Error(true))
                    }
            }
    }








}