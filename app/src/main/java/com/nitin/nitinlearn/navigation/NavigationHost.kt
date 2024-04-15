package com.nitin.nitinlearn.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.nitin.core.navigation.NavigationDestination
import com.nitin.core.navigation.NavigationFactory


@OptIn(ExperimentalAnimationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun NavigationHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    factories: Set<NavigationFactory>
) {
    Scaffold { innerPadding ->
        AnimatedNavHost(
            navController = navController,
            startDestination = NavigationDestination.Splash.route,
            modifier = modifier.padding(innerPadding),
        ) {
            factories.forEach {
                it.create(this)
            }
        }
    }
}

