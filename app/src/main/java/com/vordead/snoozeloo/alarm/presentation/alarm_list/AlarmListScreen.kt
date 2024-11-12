package com.vordead.snoozeloo.alarm.presentation.alarm_list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vordead.snoozeloo.alarm.presentation.alarm_list.components.AlarmList
import com.vordead.snoozeloo.alarm.presentation.models.AlarmListItemUi
import com.vordead.snoozeloo.core.presentation.SnoozelooBackground

@Composable
fun AlarmListScreen(
    state: AlarmListState,
    onAction: (AlarmListAction) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .statusBarsPadding()
    ) {
        Text(
            text = "Your Alarms",
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp, top = 16.dp),
            style = MaterialTheme.typography.bodyLarge.copy(
                fontSize = 24.sp,
                fontWeight = FontWeight.Medium,
                color = Color(0xFF0D0F19),
            )
        )
        AlarmList(
            alarms = state.alarms,
            onAlarmClick = { alarm ->
                onAction(AlarmListAction.onAlarmClick(alarm.id))
            },
            onAlarmSwitchClick = { alarm, isChecked ->
                onAction(AlarmListAction.onAlarmSwitchClick(alarm.id, isChecked))
            },
        )
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