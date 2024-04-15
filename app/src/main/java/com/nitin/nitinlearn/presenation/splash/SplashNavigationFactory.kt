package com.nitin.nitinlearn.presenation.splash


import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.composable
import com.nitin.core.navigation.NavigationDestination
import com.nitin.core.navigation.NavigationFactory
import javax.inject.Inject

/**
 * Home navigation factory
 * @author Anand
 *
 * @constructor Create empty Home navigation factory
 */
class SplashNavigationFactory @Inject constructor() : NavigationFactory {


    @OptIn(ExperimentalAnimationApi::class)
    override fun create(builder: NavGraphBuilder) {
        builder.composable(
            NavigationDestination.Splash.route,
            enterTransition = { slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Left,animationSpec = tween(300)) },
            exitTransition = { slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Left, animationSpec = tween(300) ) }){
            SplashScreen()
        }
    }
}