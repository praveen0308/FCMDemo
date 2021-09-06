package com.jmm.brsap.fcmdemo.services

import android.app.Notification
import android.util.Log
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import android.app.NotificationManager

import android.R
import android.graphics.Color
import com.jmm.brsap.fcmdemo.MyApplication.Companion.FCM_CHANNEL_ID


class MyFirebaseMessagingService : FirebaseMessagingService() {
    private val TAG = "MyFirebaseMessagingService"
    override fun onNewToken(p0: String) {
        super.onNewToken(p0)

    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        // ...

       
        // Not getting messages here? See why this may be: https://goo.gl/39bRNJ
        Log.d(TAG, "From: ${remoteMessage.from}")

        if(remoteMessage.notification!=null){
            val title = remoteMessage.notification!!.title
            val body = remoteMessage.notification!!.body

            val notification: Notification = NotificationCompat.Builder(this, FCM_CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_menu_help)
                .setContentTitle(title)
                .setContentText(body)
                .setColor(Color.BLUE)
                .build()

            val manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            manager.notify(1002, notification)
        }
        // Check if message contains a data payload.
        if (remoteMessage.data.isNotEmpty()) {
            Log.d(TAG, "Message data payload: ${remoteMessage.data}")

            if (/* Check if data needs to be processed by long running job */ true) {
                // For long-running tasks (10 seconds or more) use WorkManager.
//                scheduleJob()
            } else {
                // Handle message within 10 seconds
//                handleNow()
            }
        }

        // Check if message contains a notification payload.
        remoteMessage.notification?.let {
            Log.d(TAG, "Message Notification Body: ${it.body}")
        }

        // Also if you intend on generating your own notifications as a result of a received FCM
        // message, here is where that should be initiated. See sendNotification method below.
    }
}