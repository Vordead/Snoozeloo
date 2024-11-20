package com.vordead.snoozeloo.alarm.presentation.alarm_list

sealed interface AlarmListAction {
    data object onCreateAlarmClick : AlarmListAction
    data class onAlarmClick(val alarmId: Int) : AlarmListAction
    data class onAlarmSwitchClick(val alarmId: Int, val isChecked: Boolean) : AlarmListAction
}