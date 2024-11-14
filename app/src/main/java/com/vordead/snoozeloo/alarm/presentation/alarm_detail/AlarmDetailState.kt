package com.vordead.snoozeloo.alarm.presentation.alarm_detail

import androidx.compose.runtime.Immutable

@Immutable
data class AlarmDetailState(
    val alarmId: String? = null,
    val alarmName: String? = null
)