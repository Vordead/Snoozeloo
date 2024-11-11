package com.vordead.snoozeloo.alarm.presentation.alarm_detail.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.dialog
import com.vordead.snoozeloo.alarm.presentation.alarm_detail.AlarmDetailDialog
import kotlinx.serialization.Serializable

@Serializable
data class AlarmDetail(val id: String)

fun NavController.navigateToAlarmDetail(id: String) = navigate(AlarmDetail(id))


fun NavGraphBuilder.alarmDetailDestination(){
    dialog<AlarmDetail> { backStackEntry ->
        val alarmId = backStackEntry.arguments?.getString("id") ?: return@dialog
        AlarmDetailDialog(alarmId = alarmId)
    }
}