package com.vordead.snoozeloo.alarm.presentation.models

import com.vordead.snoozeloo.alarm.domain.models.Alarm

data class AlarmUi(
    val id: Int? = null,
    val title: String? = null,
    val time: String,
    val period: String,
    val isEnabled: Boolean = false,
    val remainingTime: String
)


fun Alarm.toAlarmUi(): AlarmUi {
    return AlarmUi(
        id = id,
        title = alarmName ?: "Alarm",
        time = time,
        period = repeatDays.joinToString(", "),
        isEnabled = isEnabled,
        remainingTime = "Remaining time"
    )
}

fun AlarmUi.toAlarm(): Alarm {
    return Alarm(
        id = id,
        alarmName = title,
        time = time,
        repeatDays = period.split(", "),
        isEnabled = isEnabled
    )
}
