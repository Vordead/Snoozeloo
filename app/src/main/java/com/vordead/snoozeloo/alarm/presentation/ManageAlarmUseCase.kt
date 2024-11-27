package com.vordead.snoozeloo.alarm.presentation

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import java.util.Calendar
import javax.inject.Inject

class ManageAlarmUseCase @Inject constructor(
    @ApplicationContext private val context: Context
) {
    fun scheduleAlarm(alarmId: Int, triggerTime: Calendar) {
        AlarmScheduler.scheduleAlarm(context, alarmId, triggerTime)
    }

    fun unscheduleAlarm(alarmId: Int) {
        AlarmScheduler.cancelAlarm(context, alarmId)
    }
}