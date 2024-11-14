package com.vordead.snoozeloo.alarm.presentation.alarm_list

sealed interface AlarmListAction {
    data object onCreateAlarmClick : AlarmListAction
    data class onAlarmClick(val alarmId: String) : AlarmListAction
    data class onAlarmSwitchClick(val alarmId: String, val isChecked: Boolean) : AlarmListAction
}