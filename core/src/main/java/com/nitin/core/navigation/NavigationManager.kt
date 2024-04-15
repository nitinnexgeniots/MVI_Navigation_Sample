package com.nitin.core.navigation
import com.nitin.core.navigation.NavigationCommand
import kotlinx.coroutines.flow.Flow


interface NavigationManager {
    val navigationEvent: Flow<NavigationCommand>
    fun navigate(command: NavigationCommand)
}
