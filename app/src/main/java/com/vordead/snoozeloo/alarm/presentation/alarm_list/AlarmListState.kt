package com.vordead.snoozeloo.alarm.presentation.alarm_list

import androidx.compose.runtime.Immutable
import com.vordead.snoozeloo.alarm.presentation.models.AlarmUi

@Immutable
data class AlarmListState(
    val alarms: List<AlarmUi> = emptyList(),
)