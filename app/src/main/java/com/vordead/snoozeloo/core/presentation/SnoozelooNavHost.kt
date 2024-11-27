package com.vordead.snoozeloo.core.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.vordead.snoozeloo.alarm.presentation.alarm_detail.navigation.alarmDetailDestination
import com.vordead.snoozeloo.alarm.presentation.alarm_detail.navigation.navigateToAlarmDetail
import com.vordead.snoozeloo.alarm.presentation.alarm_list.navigation.AlarmListNavigation
import com.vordead.snoozeloo.alarm.presentation.alarm_list.navigation.alarmListDestination
import com.vordead.snoozeloo.alarm.presentation.ringing.navigation.alarmRingingDestination

@Composable
fun SnoozelooNavHost(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = AlarmListNavigation,
        modifier = modifier
    ) {
        alarmListDestination(onNavigateToAlarmDetail = { alarmId ->
            navController.navigateToAlarmDetail(alarmId)
        })
        alarmDetailDestination(
            onNavigateBack = {
                navController.navigateUp()
            },
            onNavigateToRingtoneSettings = {

            }
        )

        alarmRingingDestination(onNavigateBack = {
            navController.navigateUp()
        })

    }
}