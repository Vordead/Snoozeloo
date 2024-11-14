package com.vordead.snoozeloo.alarm.presentation.alarm_detail

sealed interface AlarmDetailAction {
    data object onSaveClick : AlarmDetailAction
    data object onBackClick : AlarmDetailAction
    data object onAlarmNameClick : AlarmDetailAction
    data class onTimeChange(val hour: Int, val minute: Int) : AlarmDetailAction
}