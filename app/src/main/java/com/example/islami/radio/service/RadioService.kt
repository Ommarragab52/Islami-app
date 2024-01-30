package com.example.islami.radio.service

import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.media.MediaPlayer
import android.net.Uri
import android.os.Binder
import android.os.IBinder
import android.util.Log
import android.widget.RemoteViews
import androidx.core.app.NotificationCompat
import com.example.islami.CONSTANTS
import com.example.islami.R

class RadioService : Service() {
    private val binder = MyBinder()
    private var mp: MediaPlayer = MediaPlayer()
    private var radioName = ""

    inner class MyBinder : Binder() {
        fun getRadioServices(): RadioService = this@RadioService
    }

    override fun onBind(intent: Intent?): IBinder {
        return binder
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val radioName = intent?.getStringExtra(CONSTANTS.RADIO_NAME)
        val radioUrl = intent?.getStringExtra(CONSTANTS.RADIO_URL)
        val radioAction = intent?.getStringExtra(CONSTANTS.RADIO_ACTION)

        if (radioName != null && radioUrl != null)
            playMediaPlayer(radioUrl, radioName)

        if (radioAction != null) {
            when (radioAction) {
                CONSTANTS.PLAY_RADIO -> {
                    playPauseMediaPlayer()
                }

                CONSTANTS.STOP_RADIO -> {
                    stopMediaPlayer()
                }
            }
        }

        return START_NOT_STICKY
    }

    fun radioIsPlaying(): Boolean {
        return mp.isPlaying
    }

    fun playMediaPlayer(radioUrl: String, radioName: String) {
        pauseMediaPlayer()
        this.radioName = radioName
        mp.setDataSource(this, Uri.parse(radioUrl))
        mp.prepareAsync()
        mp.setOnPreparedListener {
            it.start()
        }
        createRadioNotification()

    }

    private fun playPauseMediaPlayer() {
        Log.e("Radio action", "playPause ")
        if (mp.isPlaying)
            mp.pause()
        else mp.start()

        updateNotification()
    }

    private fun pauseMediaPlayer() {
        if (mp.isPlaying) {
            mp.stop()
            mp.reset()
            updateNotification()
        }
        updateNotification()
    }

    fun stopMediaPlayer() {
        if (mp.isPlaying) {
            mp.stop()
            mp.reset()

        }
        stopForeground(STOP_FOREGROUND_REMOVE)
        stopSelf()
    }

    private fun isDarkMode(): Boolean {
        val darkModeFlags = resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
        return darkModeFlags == Configuration.UI_MODE_NIGHT_YES
    }

    private fun customNotification(): RemoteViews {
        val contentView = RemoteViews(packageName, R.layout.radio_notifaction_view)
        contentView.setTextViewText(R.id.title, radioName)
        contentView.setImageViewResource(
            R.id.play,
            if (!mp.isPlaying) {
                if (!isDarkMode())
                    R.drawable.ic_play_notification
                else R.drawable.ic_play_notification_night
            } else {
                if (!isDarkMode())
                    R.drawable.ic_pause
                else R.drawable.ic_pause_night
            }
        )
        contentView.setImageViewResource(
            R.id.stop,
            if (!isDarkMode())
                R.drawable.ic_stop_notification
            else R.drawable.ic_stop_notification_night
        )

        contentView.setOnClickPendingIntent(R.id.play, getPlayButtonPendingIntent())
        contentView.setOnClickPendingIntent(R.id.stop, getStopButtonPendingIntent())

        return contentView
    }

    private fun createRadioNotification() {
        val notification = NotificationCompat.Builder(this@RadioService, CONSTANTS.RADIO_CHANNEL)
        notification.setSmallIcon(R.drawable.ic_small_quran)
            .setCustomContentView(customNotification())
            .setStyle(NotificationCompat.DecoratedCustomViewStyle())
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setSound(null)
        startForeground(12, notification.build())
    }

    private fun updateNotification() {
        val notification = NotificationCompat.Builder(this@RadioService, CONSTANTS.RADIO_CHANNEL)
        notification.setSmallIcon(R.drawable.ic_small_quran)
            .setCustomContentView(customNotification())
            .setStyle(NotificationCompat.DecoratedCustomViewStyle())
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setSound(null)
        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(12, notification.build())

    }

    private fun getStopButtonPendingIntent(): PendingIntent? {
        val intent = Intent(this@RadioService, RadioService::class.java)
        intent.putExtra(CONSTANTS.RADIO_ACTION, CONSTANTS.STOP_RADIO)
        return PendingIntent.getService(this@RadioService, 10, intent, PendingIntent.FLAG_IMMUTABLE)
    }

    private fun getPlayButtonPendingIntent(): PendingIntent? {
        val intent = Intent(this@RadioService, RadioService::class.java)
        intent.putExtra(CONSTANTS.RADIO_ACTION, CONSTANTS.PLAY_RADIO)
        return PendingIntent.getService(this@RadioService, 11, intent, PendingIntent.FLAG_IMMUTABLE)

    }


}
