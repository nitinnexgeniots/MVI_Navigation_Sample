package com.nitin.nitinlearn.presenation.notification

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class CounterNotificationReciever: BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent?) {
        val service=CounterNotificationService(context)
        service.showNotification(++Counter.value)
        println("Nitin ${Counter.value}")
    }
}