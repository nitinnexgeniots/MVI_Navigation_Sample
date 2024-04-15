package com.nitin.nitinlearn

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.nitin.core.extensions.collectWithLifecycle
import com.nitin.core.navigation.NavigationDestination
import com.nitin.core.navigation.NavigationFactory
import com.nitin.core.navigation.NavigationManager
import com.nitin.network.domain.usecases.GetSampleApiQuotesDataUseCase
import com.nitin.nitinlearn.navigation.NavigationHost
import com.nitin.nitinlearn.ui.theme.NitinLearnTheme
import dagger.hilt.android.AndroidEntryPoint

import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var navigationFactories: @JvmSuppressWildcards Set<NavigationFactory>

    @Inject
    lateinit var navigationManager: NavigationManager



    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NitinLearnTheme {
                val navController = rememberAnimatedNavController()
               val systemUiController = rememberSystemUiController()
                SideEffect {
                    systemUiController.setStatusBarColor(color = Color.Transparent)
                }
                Scaffold {
                    NavigationHost(
                        modifier = Modifier
                            .padding(it),
                        navController = navController,
                        factories = navigationFactories
                    )
                }

                navigationManager
                    .navigationEvent
                    .collectWithLifecycle(
                        key = navController
                    ) {
                        when(it.destination){
                            NavigationDestination.PopBackStack.route -> {
                                if(!navController.popBackStack()) moveTaskToBack(true)
                            }
                            else -> navController.navigate(it.destination, it.configuration)
                        }
                    }
            }
        }
    }


    override fun onStart() {
        super.onStart()
    }

    override fun onResume() {
        super.onResume()
    }


    override fun onPause() {
        super.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

}










