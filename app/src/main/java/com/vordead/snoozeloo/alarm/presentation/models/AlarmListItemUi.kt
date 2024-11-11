package com.vordead.snoozeloo.alarm.presentation.models

data class AlarmListItemUi(
    val id: String,
    val title: String,
    val time: String,
    val period: String,
    val isEnabled: Boolean,
    val remainingTime: String
)