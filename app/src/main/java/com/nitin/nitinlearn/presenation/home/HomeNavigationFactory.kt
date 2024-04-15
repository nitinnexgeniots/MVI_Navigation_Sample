package com.nitin.nitinlearn.presenation.home

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.composable
import com.nitin.core.navigation.NavigationDestination
import com.nitin.core.navigation.NavigationFactory
import com.nitin.nitinlearn.presenation.navmenu.NavMenu
import javax.inject.Inject

/**
 * Home navigation factory
 * @author Anand
 *
 * @constructor Create empty Home navigation factory
 */
class HomeNavigationFactory @Inject constructor() : NavigationFactory {


    @OptIn(ExperimentalAnimationApi::class)
    override fun create(builder: NavGraphBuilder) {
        builder.composable(
            NavigationDestination.Home.route,
            enterTransition = { slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Right,animationSpec = tween(500)) },
            exitTransition = { slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Left, animationSpec = tween(500) ) },
            popExitTransition = { slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Left, animationSpec = tween(500) ) },
            popEnterTransition = { slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Right, animationSpec = tween(500) ) }
            ) {
                Home()
        }

        builder.composable(NavigationDestination.NavMenu.route,
            enterTransition = { slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Right,animationSpec = tween(500)) },
            exitTransition = { slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Left, animationSpec = tween(500) ) },
            popExitTransition = {slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Left, animationSpec = tween(500) ) },
            popEnterTransition = {slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Left, animationSpec = tween(500) ) }){
            NavMenu()
        }

    }
}
