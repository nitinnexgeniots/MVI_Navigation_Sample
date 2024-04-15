package com.nitin.nitinlearn

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import com.nitin.nitinlearn.presenation.notification.CounterNotificationService
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class AppCMSApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CounterNotificationService.counter_notification_id,
                "Settings Name",
                NotificationManager.IMPORTANCE_HIGH
            )
            channel.description = "This is a just description of your channel to tell the user"
            val notificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
}

