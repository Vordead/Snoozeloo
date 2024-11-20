package com.vordead.snoozeloo.alarm.presentation.alarm_detail.components.time_input

import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember

@Stable
class TimeInputState(
    val hourState : TextFieldState,
    val minuteState: TextFieldState
)

@Composable
fun rememberTimeInputState(
    initialHour: String ="",
    initialMinute: String=""
): TimeInputState {
    val hourTextInputState = rememberTextFieldState(initialHour)
    val minuteTextInputState = rememberTextFieldState(initialMinute)
    return remember(initialHour,initialMinute) {
        hourTextInputState.edit{
            append(initialHour)
        }
        minuteTextInputState.edit{
            append(initialMinute)
        }
        TimeInputState(hourTextInputState, minuteTextInputState)
    }
}