package com.vordead.snoozeloo

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.app.TaskStackBuilder
import androidx.core.content.getSystemService
import androidx.core.net.toUri
import dagger.hilt.android.HiltAndroidApp

const val DEEPLINK_DOMAIN = "vordead.com"
@HiltAndroidApp
class SnoozelooApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()
        showNotification()
    }


    private fun createNotificationChannel() {
        val channel = NotificationChannel(
            "alarm_channel", "Alarm Notifications",
            NotificationManager.IMPORTANCE_HIGH
        )
        val notificationManager = getSystemService<NotificationManager>()!!
        notificationManager.createNotificationChannel(channel)
    }

    private fun showNotification() {
        val activityIntent = Intent(this, MainActivity::class.java).apply {
            data = "https://$DEEPLINK_DOMAIN/81".toUri()
        }
        val pendingIntent = TaskStackBuilder.create(this).run {
            addNextIntentWithParentStack(activityIntent)
            getPendingIntent(0, PendingIntent.FLAG_IMMUTABLE)
        }

        val notification = NotificationCompat.Builder(this, "alarm_channel")
            .setContentTitle("Alarm Ringing")
            .setContentText("Your alarm is ringing")
            .setSmallIcon(R.drawable.ic_alarm)
            .setContentIntent(pendingIntent)
            .setFullScreenIntent(pendingIntent, true)
            .setAutoCancel(true)
            .build()

        val notificationManager = getSystemService<NotificationManager>()!!
        notificationManager.notify(1, notification)
    }

}