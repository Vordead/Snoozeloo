package com.vordead.snoozeloo.alarm.presentation.alarm_list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vordead.snoozeloo.alarm.presentation.alarm_list.components.AddAlarmFab
import com.vordead.snoozeloo.alarm.presentation.alarm_list.components.AlarmList
import com.vordead.snoozeloo.alarm.presentation.alarm_list.components.AppBar
import com.vordead.snoozeloo.alarm.presentation.models.AlarmListItemUi
import com.vordead.snoozeloo.core.presentation.SnoozelooBackground

@Composable
fun AlarmListScreen(
    state: AlarmListState,
    onAction: (AlarmListAction) -> Unit,
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        floatingActionButton = {
            AddAlarmFab(
                onClick = { AlarmListAction.onCreateAlarmClick },
                modifier = Modifier
                    .padding(bottom = 20.dp)
            )
        },
        floatingActionButtonPosition = FabPosition.Center,
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .statusBarsPadding()
                    .consumeWindowInsets(paddingValues)
            ) {
                AppBar(title = "Your Alarms")
                AlarmList(
                    alarms = state.alarms,
                    onAlarmClick = { alarm ->
                        onAction(AlarmListAction.onAlarmClick(alarm.id))
                    },
                    onAlarmSwitchClick = { alarm, isChecked ->
                        onAction(AlarmListAction.onAlarmSwitchClick(alarm.id, isChecked))
                    },
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    )
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