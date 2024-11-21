package com.vordead.snoozeloo.alarm.presentation.models

import com.vordead.snoozeloo.alarm.domain.models.Alarm
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.Locale

data class AlarmUi(
    val id: Int? = null,
    val title: String? = null,
    val hour: Int,
    val minute: Int,
    val period: String,
    val isEnabled: Boolean = false,
    val remainingTime: String? = null,
    val repeatDays: List<DayOfWeek>
) {
    val time: String
        get() = String.format(Locale.getDefault(), "%02d:%02d", hour, minute)
}

enum class DayOfWeek {
    Mo, Tu, We, Th, Fr, Sa, Su
}


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

fun AlarmUi.toAlarm(): Alarm {
    val hour24 = if (period == "AM") hour % 12 else (hour % 12) + 12
    val time = String.format("%02d:%02d", hour24, minute)

    return Alarm(
        id = id,
        alarmName = title,
        time = time,
        repeatDays = repeatDays.map { it.name },
        isEnabled = isEnabled
    )
}

fun calculateRemainingTime(alarmTime: LocalTime): String {
    val now = LocalDateTime.now()
    val alarmDateTime =
        if (alarmTime.isAfter(now.toLocalTime()) || alarmTime == now.toLocalTime()) {
            now.withHour(alarmTime.hour).withMinute(alarmTime.minute).withSecond(0).withNano(0)
        } else {
            now.plusDays(1).withHour(alarmTime.hour).withMinute(alarmTime.minute).withSecond(0)
                .withNano(0)
        }

    val duration = java.time.Duration.between(now, alarmDateTime)
    val hours = duration.toHours()
    val minutes = duration.toMinutes() % 60

    return when {
        hours > 0 -> "Alarm in ${hours}h ${minutes}min"
        else -> "Alarm in ${minutes}min"
    }
}