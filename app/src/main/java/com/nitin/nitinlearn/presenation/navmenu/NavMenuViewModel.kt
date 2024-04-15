package com.nitin.nitinlearn.presenation.navmenu

import androidx.lifecycle.SavedStateHandle
import com.nitin.core.base.BaseViewModel
import com.nitin.core.navigation.NavigationManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

//@HiltViewModel
class NavMenuViewModel
{
//
//@Inject constructor(
//    val savedStateHandle: SavedStateHandle,
//    navMenuInitialState: NavMenuUiState,
//    val navigationManager: NavigationManager,
//) : BaseViewModel<NavMenuUiState, NavMenuUiState.PartialState, NavMenuEvent, NavMenuIntent>(
//    savedStateHandle,
//    navMenuInitialState
//) {
//    override fun mapIntents(intent: NavMenuIntent): Flow<NavMenuUiState.PartialState> {
//        TODO("Not yet implemented")
//    }
//
//    override fun reduceUiState(
//        previousState: NavMenuUiState,
//        partialState: NavMenuUiState.PartialState
//    ): NavMenuUiState {
//        TODO("Not yet implemented")
//    }
}