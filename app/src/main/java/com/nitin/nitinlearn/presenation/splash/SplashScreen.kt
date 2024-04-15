package com.nitin.nitinlearn.presenation.splash

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.media3.common.util.UnstableApi
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavOptions
import com.nitin.core.navigation.NavigationCommand
import com.nitin.core.navigation.NavigationDestination
import com.nitin.nitinlearn.presenation.notification.Counter
import com.nitin.nitinlearn.presenation.notification.CounterNotificationService

@androidx.annotation.OptIn(UnstableApi::class)
@Composable
fun SplashScreen(viewModel: SplashViewModel = hiltViewModel()) {
    val counterNotificationService=CounterNotificationService(LocalContext.current)
    Column(horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()) {
        Text(
            text = "Welcome To Splash Screen and Click To Go HomeScreen",
            modifier = Modifier.clickable {
                val navDestination =  NavigationDestination.Home.route
                viewModel.navigationManager.navigate(object : NavigationCommand {
                    override val destination = navDestination
                    override val configuration: NavOptions = NavOptions.Builder().setPopUpTo(
                        NavigationDestination.Splash.route,
                        inclusive = true,
                        saveState = false
                    ).build()
                }) },
            fontWeight = FontWeight.SemiBold,
            fontSize = 14.sp
        )
        Button(onClick =
        {
            counterNotificationService.showNotification(Counter.value)
            println("Nitin ${Counter.value}")
        }) {
            Text(text = "Display Notification")
        }
    }

}