package com.nitin.nitinlearn.presenation.home

import android.app.Application
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Build
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.List
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Settings
import androidx.lifecycle.SavedStateHandle
import com.nitin.core.base.BaseViewModel
import com.nitin.core.navigation.NavigationManager
import com.nitin.nitinlearn.presenation.navmenu.TabBarItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    homeInitialState: HomeUiState,
    private val application: Application,
    val navigationManager: NavigationManager,
) : BaseViewModel<HomeUiState, HomeUiState.PartialState, HomeEvent, HomeIntent>(
    savedStateHandle,
    homeInitialState
) {
    override fun mapIntents(intent: HomeIntent): Flow<HomeUiState.PartialState> {
        TODO("Not yet implemented")
    }

    override fun reduceUiState(
        previousState: HomeUiState,
        partialState: HomeUiState.PartialState
    ): HomeUiState {
        TODO("Not yet implemented")
    }

     fun fetchNavigationList(): List<TabBarItem> {
        // setting up the individual tabs
        val homeTab = TabBarItem(
            title = "Home",
            selectedIcon = Icons.Filled.Home,
            unselectedIcon = Icons.Outlined.Home
        )
        val alertsTab = TabBarItem(
            title = "Alerts",
            selectedIcon = Icons.Filled.Notifications,
            unselectedIcon = Icons.Outlined.Notifications,

            )
        val settingsTab = TabBarItem(
            title = "Settings",
            selectedIcon = Icons.Filled.Settings,
            unselectedIcon = Icons.Outlined.Settings
        )

         val privacyTab = TabBarItem(
             title = "Privacy",
             selectedIcon = Icons.Filled.Build,
             unselectedIcon = Icons.Outlined.Build
         )

        val moreTab = TabBarItem(
            title = "More",
            selectedIcon = Icons.Filled.List,
            unselectedIcon = Icons.Outlined.List
        )

        // creating a list of all the tabs
        return listOf(homeTab, alertsTab, settingsTab, privacyTab,moreTab)

    }

    fun isOdd(number: Int): Boolean {
        return number % 2 != 0
    }
    fun getPlayerScore(diceResult: Int, i: Int): Int {
        return diceResult+i
    }

     suspend fun rollDiceAfterDelay(onDiceRolled: (Int) -> Unit) {
         delay(5000)
        val newDiceResult = (1..6).random()
        onDiceRolled(newDiceResult)
    }

}


fun getDotPositions(number: Int): List<Pair<Float, Float>> {
    return when (number) {
        1 -> listOf(Pair(0.5f, 0.5f))
        2 -> listOf(Pair(0.2f, 0.2f), Pair(0.8f, 0.8f))
        3 -> listOf(Pair(0.2f, 0.2f), Pair(0.5f, 0.5f), Pair(0.8f, 0.8f))
        4 -> listOf(Pair(0.2f, 0.2f), Pair(0.8f, 0.2f), Pair(0.2f, 0.8f), Pair(0.8f, 0.8f))
        5 -> listOf(
            Pair(0.2f, 0.2f),
            Pair(0.8f, 0.2f),
            Pair(0.2f, 0.8f),
            Pair(0.8f, 0.8f),
            Pair(0.5f, 0.5f)
        )
        6 -> listOf(
            Pair(0.2f, 0.2f),
            Pair(0.8f, 0.2f),
            Pair(0.2f, 0.8f),
            Pair(0.8f, 0.8f),
            Pair(0.2f, 0.5f),
            Pair(0.8f, 0.5f)
        )
        else -> emptyList()
    }
}
