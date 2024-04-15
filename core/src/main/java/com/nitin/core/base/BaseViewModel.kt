package com.nitin.core.base

import android.os.Parcelable
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.scan
import kotlinx.coroutines.launch
import timber.log.Timber
private const val SAVED_UI_STATE_KEY = "savedUiStateKey"

abstract class BaseViewModel<UI_STATE : Parcelable, PARTIAL_UI_STATE, EVENT, INTENT>(
    savedStateHandle: SavedStateHandle,
    initialState: UI_STATE
) : ViewModel() {
    private val intentFlow = MutableSharedFlow<INTENT>()

    val uiState = savedStateHandle.getStateFlow(SAVED_UI_STATE_KEY, initialState)

    private val eventChannel = Channel<EVENT>(Channel.BUFFERED)
    val event = eventChannel.receiveAsFlow()

    init {
        viewModelScope.launch {
            intentFlow
                .flatMapMerge { mapIntents(it) }
                .scan(uiState.value, ::reduceUiState)
                .catch {
                    emit(uiState.value)
                    Timber.e(it) }
                .collect {
                    savedStateHandle[SAVED_UI_STATE_KEY] = it
                }
        }
    }

    /**
     * Accept intent
     *
     * @param intent
     */
    fun acceptIntent(intent: INTENT) =
        viewModelScope.launch {
            intentFlow.emit(intent)
        }

    /**
     * Publish event
     *
     * @param event
     */
    protected fun publishEvent(event: EVENT) {
        viewModelScope.launch {
            eventChannel.send(event)
        }
    }

    /**
     * Map intents
     *
     * @param intent
     * @return
     */
    protected abstract fun mapIntents(intent: INTENT): Flow<PARTIAL_UI_STATE>

    /**
     * Reduce ui state
     *
     * @param previousState
     * @param partialState
     * @return
     */
    protected abstract fun reduceUiState(
        previousState: UI_STATE,
        partialState: PARTIAL_UI_STATE
    ): UI_STATE
}