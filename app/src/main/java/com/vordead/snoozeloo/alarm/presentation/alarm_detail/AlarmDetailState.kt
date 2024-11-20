package com.vordead.snoozeloo.alarm.presentation.alarm_detail

import androidx.compose.runtime.Immutable
import com.vordead.snoozeloo.alarm.presentation.models.AlarmUi

@Immutable
data class AlarmDetailState(
    val alarm: AlarmUi? = null,
    val hourField: String = "",
    val minuteField: String = "",
    val alarmName : String = "",
    val showAlarmNameDialog: Boolean = false,
) {
    val isAlarmValid: Boolean
        get() = hourField.length == 2 && minuteField.length == 2
}