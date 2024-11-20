package com.vordead.snoozeloo.alarm.presentation.alarm_list

sealed interface AlarmListEvent {
    data class NavigateToAlarmDetail(val alarmId: Int? = null) : AlarmListEvent
}