package com.vordead.snoozeloo.alarm.presentation.alarm_detail

sealed interface AlarmDetailAction {
    data class OnSaveAlarmName(val alarmName: String? = null) : AlarmDetailAction
    data object OnSaveClick : AlarmDetailAction
    data object OnBackClick : AlarmDetailAction
    data object OnAlarmNameClick : AlarmDetailAction
    data class OnTimeChange(val hour: Int, val minute: Int) : AlarmDetailAction
    data object OnDismissAlarmNameDialog : AlarmDetailAction
}