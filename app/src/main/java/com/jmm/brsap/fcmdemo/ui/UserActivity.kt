package com.jmm.brsap.fcmdemo.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.ktx.Firebase
import com.google.firebase.messaging.ktx.messaging
import com.jmm.brsap.fcmdemo.R

class UserActivity : AppCompatActivity() {
    private val TAG = "UserActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)
        Firebase.messaging.subscribeToTopic("offers")
            .addOnCompleteListener { task ->

                if (!task.isSuccessful) {
                    Log.d(TAG, "failed while subscribing")
                }
                Log.d(TAG, "subscribed")

            }
    }
}