package com.vordead.snoozeloo.alarm.presentation.alarm_list.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vordead.snoozeloo.alarm.presentation.models.AlarmListItemUi
import com.vordead.snoozeloo.core.presentation.SnoozelooBackground

@Composable
fun AlarmListItem(
    alarmListItemUi: AlarmListItemUi,
    onAlarmSwitchClick: (Boolean) -> Unit,
    onAlarmClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier.clickable { onAlarmClick() },
        shape = RoundedCornerShape(10.dp),
    ) {
        Column(
            modifier = Modifier
                .background(Color.White)
                .padding(16.dp),
        ) {
            AlarmHeader(
                title = alarmListItemUi.title,
                checked = alarmListItemUi.isEnabled,
                onCheckedChange = { onAlarmSwitchClick(it) }
            )
            TimeDisplay(time = "10:00", period = "AM")
            AlarmFooter("30min")
        }
    }
}

@Composable
fun AlarmHeader(
    title: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            modifier = Modifier.padding(bottom = 10.dp),
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold)
        )
        Switch(
            checked = checked,
            onCheckedChange = onCheckedChange,
            thumbContent = {
                Box(modifier = Modifier.size(SwitchDefaults.IconSize))
            },
            colors = SwitchDefaults.colors(
                checkedThumbColor = Color.White,
                checkedTrackColor = Color(0xFF4664FF),
                uncheckedThumbColor = Color.White,
                uncheckedTrackColor = Color(0xFFBCC6FF),
                uncheckedBorderColor = Color.Transparent
            )
        )
    }
}

@Composable
fun TimeDisplay(
    time: String,
    period: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Text(
            text = time,
            style = MaterialTheme.typography.bodyLarge.copy(
                fontSize = 42.sp,
                fontWeight = FontWeight(500),
                color = Color(0xFF0D0F19),
            )
        )
        Text(
            text = period,
            style = MaterialTheme.typography.bodyLarge.copy(
                fontSize = 24.sp,
                fontWeight = FontWeight.Medium,
                color = Color(0xFF0D0F19),
            ),
            modifier = Modifier.padding(start = 4.dp, bottom = 4.dp)
        )
    }
}

@Composable
fun AlarmFooter(
    remainingTime: String
) {
    Text(
        text = "Alarm in $remainingTime",
        modifier = Modifier.padding(top = 8.dp),
        style = MaterialTheme.typography.bodyMedium.copy(
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            color = Color(0xFF858585),
        )
    )
}

@Preview(showBackground = true)
@Composable
private fun AlarmListItemPreview() {
    SnoozelooBackground {
        AlarmListItem(
            alarmListItemUi = AlarmListItemUi(
                id = "1",
                title = "Wake Up",
                time = "10:00",
                period = "AM",
                remainingTime = "30min",
                isEnabled = true
            ),
            onAlarmClick = {},
            onAlarmSwitchClick = {}
        )
    }
}