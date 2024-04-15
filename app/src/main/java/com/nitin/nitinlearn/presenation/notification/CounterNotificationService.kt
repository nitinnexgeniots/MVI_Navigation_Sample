package com.nitin.nitinlearn.presenation.notification

import android.app.Notification
import android.app.Notification.BigTextStyle
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import com.nitin.nitinlearn.MainActivity
import com.nitin.nitinlearn.R

class CounterNotificationService(private val context: Context) {

    companion object {

        const val counter_notification_id = "Anything Channel"
        const val notification_RequestCode = 1
    }


    fun showNotification(counter: Int) {
        val notificationManager=context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val activityIntent = Intent(context, MainActivity::class.java)
        val activityPendingIntent =
            PendingIntent.getActivity(context, notification_RequestCode, activityIntent,
                PendingIntent.FLAG_IMMUTABLE)
        val incrementIntent = PendingIntent.getBroadcast(
            context, 2,
            Intent(context, CounterNotificationReciever::class.java), PendingIntent.FLAG_IMMUTABLE
        )
        val notification = Notification.Builder(context, counter_notification_id)
            .setSmallIcon(com.nitin.common.R.drawable.ic_share)
            .setContentTitle("This is a tittle")
            .setContentText("This is a content Text")
            .setContentIntent(activityPendingIntent)
            .addAction(com.nitin.common.R.drawable.ic_menu, "Intcrment", incrementIntent).build()

        notificationManager.notify(1,notification)
    }
}