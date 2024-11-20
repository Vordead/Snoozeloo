package com.vordead.snoozeloo.alarm.presentation.alarm_detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vordead.snoozeloo.alarm.presentation.alarm_detail.components.AlarmDetailAppBar
import com.vordead.snoozeloo.alarm.presentation.alarm_detail.components.AlarmFieldChangeDialog
import com.vordead.snoozeloo.alarm.presentation.alarm_detail.components.AlarmSetting
import com.vordead.snoozeloo.alarm.presentation.alarm_detail.components.AlarmTimeInput
import com.vordead.snoozeloo.core.presentation.SnoozelooBackground

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlarmDetailScreen(
    modifier: Modifier = Modifier,
    alarmId: Int? = null,
    state: AlarmDetailState,
    onAction: (AlarmDetailAction) -> Unit,
) {
    val timeInputState = rememberTimeInputState()
    LaunchedEffect(alarmId) {
        onAction(AlarmDetailAction.OnLoadAlarm(alarmId))
    }

    LaunchedEffect(state.hourField,state.minuteField) {
        timeInputState.hourState.edit{
            append(state.hourField)
        }
        timeInputState.minuteState.edit{
            append(state.minuteField)
        }
    }
    if (state.showAlarmNameDialog) {
        AlarmFieldChangeDialog(
            onSaveClicked = { onAction(AlarmDetailAction.OnSaveAlarmName(it)) },
            fieldText = state.alarm?.title ?: "",
            onDismiss = {
                onAction(AlarmDetailAction.OnDismissAlarmNameDialog)
            },
            modifier = Modifier.systemBarsPadding()
        )
    }
    Scaffold(topBar = {
        CenterAlignedTopAppBar(
            title = {
                AlarmDetailAppBar(
                    isAlarmValid = state.isAlarmValid,
                    onBackClick = { onAction(AlarmDetailAction.OnBackClick) },
                    onSaveClick = { onAction(AlarmDetailAction.OnSaveClick) },
                    modifier = Modifier
                )
            },
            modifier = Modifier.padding(start = 10.dp, end = 10.dp, top = 16.dp),
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.background
            )
        )

    }) { innerPadding ->
        Column(
            modifier = modifier
                .padding(innerPadding)
                .padding(horizontal = 16.dp, vertical = 24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            AlarmTimeInput(
                state = timeInputState
            )
            AlarmSetting(
                title = "Alarm Name",
                onClick = {
                    onAction(AlarmDetailAction.OnAlarmNameClick)
                },
                trailingContent = {
                    Text("Work", style = MaterialTheme.typography.bodyMedium)
                }
            )
        }
    }

}

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
    return remember {
        TimeInputState(hourTextInputState, minuteTextInputState)
    }
}

@Preview
@Composable
private fun AlarmDetailDialogPreview() {
    SnoozelooBackground {
        AlarmDetailScreen(
            alarmId = 1,
            state = AlarmDetailState(),
            onAction = {},
            modifier = Modifier.systemBarsPadding()
        )
    }
}