package com.example.islami

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.media.AudioAttributes
import android.os.Build

class MyApplication : Application() {
    companion object {
        const val RADIO_CHANNEL = "radioChannel"
    }

    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                RADIO_CHANNEL,
                "Islami notification channel",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            channel.setSound(null, AudioAttributes.Builder().build())

            val notificationManager =
                getSystemService(NotificationManager::class.java) as NotificationManager
            notificationManager.createNotificationChannel(channel)

        }
    }

}
