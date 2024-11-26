package com.vordead.snoozeloo.alarm.presentation

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.net.toUri
import com.vordead.snoozeloo.MainActivity
import com.vordead.snoozeloo.R

object NotificationHelper {

    fun showNotification(context: Context, alarmId: Int) {
        val channelId = "alarm_channel"
        val channelName = "Alarm Notifications"
        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val channel = NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_HIGH)
        notificationManager.createNotificationChannel(channel)

        val fullScreenIntent = Intent(
            Intent.ACTION_VIEW,
            "snoozeloo://ringing/$alarmId".toUri(),
            context,
            MainActivity::class.java
        ).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        val fullScreenPendingIntent = PendingIntent.getActivity(context, alarmId, fullScreenIntent, PendingIntent.FLAG_UPDATE_CURRENT)

        val notification = NotificationCompat.Builder(context, channelId)
            .setContentTitle("Alarm Ringing")
            .setContentText("Your alarm is ringing")
            .setSmallIcon(R.drawable.ic_alarm)
            .setContentIntent(fullScreenPendingIntent)
            .setFullScreenIntent(fullScreenPendingIntent, true)
            .setAutoCancel(true)
            .build()

        notificationManager.notify(alarmId, notification)
    }
}