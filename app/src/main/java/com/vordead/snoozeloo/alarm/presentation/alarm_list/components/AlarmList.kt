package com.vordead.snoozeloo.alarm.presentation.alarm_list.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.vordead.snoozeloo.alarm.presentation.alarm_list.AlarmListAction
import com.vordead.snoozeloo.alarm.presentation.models.AlarmListItemUi

@Composable
fun AlarmList(
    alarms: List<AlarmListItemUi>,
    onAlarmClick: (AlarmListItemUi) -> Unit,
    onAlarmSwitchClick: (AlarmListItemUi, Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(alarms) { alarm ->
            AlarmListItem(
                alarmListItemUi = alarm,
                onAlarmClick = {
                    onAlarmClick(alarm)
                },
                onAlarmSwitchClick = { isChecked ->
                    onAlarmSwitchClick(alarm, isChecked)
                }
            )
        }
    }
}