package com.vordead.snoozeloo.alarm.presentation.ringing

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import java.time.LocalTime
import java.time.format.DateTimeFormatter

@Composable
fun RingingScreen(
    alarmId: Int?,
    modifier: Modifier = Modifier
) {
    // Fetch the alarm details using the alarmId
//    val alarm = getAlarmById(alarmId) // Implement this function to fetch the alarm

    val currentTime = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm"))

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Current Time: $currentTime")
        Text(text = "Alarm Time: ${alarmId}")
        Button(onClick = { /* Turn off the alarm */ }) {
            Text(text = "Turn Off")
        }
        Button(onClick = { /* Snooze the alarm */ }) {
            Text(text = "Snooze")
        }
    }
}