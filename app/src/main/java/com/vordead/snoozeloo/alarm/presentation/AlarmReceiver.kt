package com.vordead.snoozeloo.alarm.presentation

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.net.toUri
import com.vordead.snoozeloo.MainActivity

class AlarmReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val alarmId = intent.getIntExtra("alarmId", -1)
        if (alarmId != -1) {
            val deepLinkIntent = Intent(
                Intent.ACTION_VIEW,
                "snoozeloo://ringing/$alarmId".toUri(),
                context,
                MainActivity::class.java
            ).apply {
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }
            context.startActivity(deepLinkIntent)
        }
    }
}