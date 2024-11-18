package com.vordead.snoozeloo.alarm.presentation.alarm_detail

sealed interface AlarmDetailEvent {
    data object NavigateBack : AlarmDetailEvent
}