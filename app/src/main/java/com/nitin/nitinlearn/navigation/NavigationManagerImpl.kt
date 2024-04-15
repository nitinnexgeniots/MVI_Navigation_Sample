package com.nitin.nitinlearn.navigation

import com.nitin.core.navigation.NavigationCommand
import com.nitin.core.navigation.NavigationManager
import com.nitin.core.di.MainImmediateScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


class NavigationManagerImpl @Inject constructor(
    @MainImmediateScope private val externalMainImmediateScope: CoroutineScope
) : NavigationManager {
    private val navigationCommandChannel = Channel<NavigationCommand>(Channel.BUFFERED)
    override val navigationEvent = navigationCommandChannel.receiveAsFlow()

    override fun navigate(command: NavigationCommand) {
        externalMainImmediateScope.launch {
            navigationCommandChannel.send(command)
        }
    }
}
