package com.vordead.snoozeloo.alarm.presentation.alarm_list

interface AlarmListAction {
    data object onCreateAlarmClick : AlarmListAction
    data class onAlarmSwitchClick(val alarmId: String, val isChecked: Boolean) : AlarmListAction
}