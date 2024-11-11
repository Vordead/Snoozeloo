package com.vordead.snoozeloo.alarm.presentation.alarm_list

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.vordead.snoozeloo.alarm.presentation.alarm_list.components.AlarmListItem

@Composable
fun AlarmListScreen(
    state: AlarmListState,
    onAction : (AlarmListAction) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn {
        items(state.alarms) { alarm ->
            AlarmListItem(
                alarmListItemUi = alarm,
                onAlarmSwitchClick = { isChecked ->
                    onAction(AlarmListAction.onAlarmSwitchClick(alarm.id, isChecked))
                }
            )
        }
    }
}