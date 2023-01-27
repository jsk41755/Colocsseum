package com.jeongseunggyu.colocsseum.fcm

import android.os.Handler
import android.os.Looper
import android.widget.Toast
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFCM : FirebaseMessagingService() {

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)

        val myHandler = Handler(Looper.getMainLooper())

        myHandler.post {

            Toast.makeText(applicationContext, message.notification!!.title, Toast.LENGTH_SHORT).show()

        }
    }
}