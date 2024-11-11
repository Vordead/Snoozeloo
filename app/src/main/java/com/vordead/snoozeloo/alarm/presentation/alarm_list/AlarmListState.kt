package com.vordead.snoozeloo.alarm.presentation.alarm_list

import androidx.compose.runtime.Immutable
import com.vordead.snoozeloo.alarm.presentation.models.AlarmListItemUi

@Immutable
data class AlarmListState(
    val alarms: List<AlarmListItemUi> = emptyList(),
)