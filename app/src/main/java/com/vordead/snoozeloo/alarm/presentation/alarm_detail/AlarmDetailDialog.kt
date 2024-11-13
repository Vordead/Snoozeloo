package com.vordead.snoozeloo.alarm.presentation.alarm_detail

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.vordead.snoozeloo.alarm.presentation.alarm_detail.components.AlarmDetailAppBar
import com.vordead.snoozeloo.core.presentation.SnoozelooBackground

@Composable
fun AlarmDetailDialog(
    modifier: Modifier = Modifier,
    alarmId: String? = null,
) {
    Column(modifier = modifier) {
        AlarmDetailAppBar(
            onBackClick = {},
            onSaveClick = {},
            modifier = Modifier
        )
    }
}

@Preview
@Composable
private fun AlarmDetailDialogPreview() {
    SnoozelooBackground {
        AlarmDetailDialog(alarmId = "1")
    }
}