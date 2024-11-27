package com.vordead.snoozeloo.alarm.domain.models

data class Alarm(
    val id: Int? = null,
    val time: String, // Format "HH:mm"
    val isEnabled: Boolean,
    val repeatDays: List<String>,
    val alarmName: String? = null,
    val ringtone: String? = null,
    val volume: Int = 50,
    val vibrate: Boolean = true
)