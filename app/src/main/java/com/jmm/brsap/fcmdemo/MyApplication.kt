package com.jmm.brsap.fcmdemo

import android.app.Application
import android.app.NotificationManager

import android.app.NotificationChannel
import android.os.Build
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplication : Application() {
    companion object{
        const val FCM_CHANNEL_ID = "DEFAULT_CHANNEL_ID"
    }
    override fun onCreate() {
        super.onCreate()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val fcmChannel = NotificationChannel(
                FCM_CHANNEL_ID, "FCM_Channel", NotificationManager.IMPORTANCE_HIGH
            )
            val manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(fcmChannel)
        }

    }
}