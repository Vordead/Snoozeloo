package com.vordead.snoozeloo.alarm.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.vordead.snoozeloo.alarm.presentation.alarm_detail.navigation.alarmDetailDestination
import com.vordead.snoozeloo.alarm.presentation.alarm_detail.navigation.navigateToAlarmDetail
import com.vordead.snoozeloo.alarm.presentation.alarm_list.navigation.AlarmListNavigation
import com.vordead.snoozeloo.alarm.presentation.alarm_list.navigation.alarmListDestination

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
            onNavigateToAlarmDialog = {

            }
        )
    }
}