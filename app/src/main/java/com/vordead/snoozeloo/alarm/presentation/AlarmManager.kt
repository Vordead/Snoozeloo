package com.vordead.snoozeloo.alarm.presentation

import com.vordead.snoozeloo.alarm.domain.models.Alarm
import com.vordead.snoozeloo.alarm.presentation.models.AlarmUi
import com.vordead.snoozeloo.alarm.presentation.models.DayOfWeek
import com.vordead.snoozeloo.alarm.presentation.models.calculateRemainingTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter

object AlarmManager {
    fun Alarm.toAlarmUi(): AlarmUi {
        val localTime = LocalTime.parse(time, DateTimeFormatter.ofPattern("HH:mm"))
        val hour = if (localTime.hour % 12 == 0) 12 else localTime.hour % 12
        val period = if (localTime.hour < 12) "AM" else "PM"
        val remainingTime = calculateRemainingTime(localTime)

        return AlarmUi(
            id = id,
            title = alarmName ?: "Alarm",
            hour = hour,
            minute = localTime.minute,
            period = period,
            isEnabled = isEnabled,
            remainingTime = remainingTime,
            repeatDays = repeatDays.map { DayOfWeek.valueOf(it) }
        )
    }
}