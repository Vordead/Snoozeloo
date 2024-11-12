package com.vordead.snoozeloo.alarm.presentation.alarm_list

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.vordead.snoozeloo.alarm.presentation.alarm_list.components.AlarmListItem
import com.vordead.snoozeloo.alarm.presentation.models.AlarmListItemUi
import com.vordead.snoozeloo.core.presentation.SnoozelooBackground

@Composable
fun AlarmListScreen(
    state: AlarmListState,
    onAction: (AlarmListAction) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.fillMaxSize()
    ) {
        items(state.alarms) { alarm ->
            AlarmListItem(
                alarmListItemUi = alarm,
                onAlarmClick = {
                    onAction(AlarmListAction.onAlarmClick(alarm.id))
                },
                onAlarmSwitchClick = { isChecked ->
                    onAction(AlarmListAction.onAlarmSwitchClick(alarm.id, isChecked))
                }
            )
        }
    }
}

@Preview
@Composable
private fun AlarmListScreenPreview() {
    SnoozelooBackground {
        AlarmListScreen(
            state = AlarmListState(
                alarms = listOf(
                    AlarmListItemUi(
                        id = "1",
                        title = "Alarm 1",
                        time = "10:00",
                        period = "AM",
                        remainingTime = "30min",
                        isEnabled = true
                    ),
                )
            ),
            onAction = {}
        )
    }
}