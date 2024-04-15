package com.nitin.uimodule.alerts

import android.app.Application
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.lifecycle.SavedStateHandle
import com.nitin.core.base.BaseViewModel
import com.nitin.network.domain.usecases.GetSampleApiQuotesDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart
import timber.log.Timber
import javax.inject.Inject



@HiltViewModel
class AlertViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    alertInitialState: AlertUiState,
    val sampleApiQuotesDataUseCase: GetSampleApiQuotesDataUseCase,
    ) : BaseViewModel<AlertUiState, AlertUiState.PartialState, AlertEvent, AlertDataIntent>(
    savedStateHandle,
    alertInitialState
) {
    override fun mapIntents(intent: AlertDataIntent): Flow<AlertUiState.PartialState> =
        when (intent) {
            is AlertDataIntent.GetAlertsData -> getAlertsData()
        }

    private fun getAlertsData(): Flow<AlertUiState.PartialState> = flow {
        val teamDataUrl = "/quotes"
        sampleApiQuotesDataUseCase(teamDataUrl).onStart {
            emit(AlertUiState.PartialState.Loading(true))
        }.collect { result ->
            result.onSuccess {
                emit(AlertUiState.PartialState.AlertsData(it))
                Timber.d("getTeamData success")
            }
            result.onFailure {
               emit(AlertUiState.PartialState.Error(true))
                Timber.d("getTeamData failure")
            }
        }
    }

    override fun reduceUiState(
        previousState: AlertUiState,
        partialState: AlertUiState.PartialState
    ):AlertUiState = when (partialState) {

        is AlertUiState.PartialState.Loading -> previousState.copy(
            isError = false,
            isLoading = partialState.isLoading,
        )
        is AlertUiState.PartialState.Error -> previousState.copy(
            isError = partialState.isError,
        )
        is AlertUiState.PartialState.AlertsData -> previousState.copy(
            isError = false,
            isLoading = false,
            alertsDataResponse = partialState.response

        )



    }


}