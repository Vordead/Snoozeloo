package com.vordead.snoozeloo.alarm.presentation.alarm_detail.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.dialog
import androidx.navigation.toRoute
import com.vordead.snoozeloo.alarm.presentation.alarm_detail.AlarmDetailDialog
import kotlinx.serialization.Serializable

@Serializable
data class AlarmDetail(val id: String? = null)

fun NavController.navigateToAlarmDetail(id: String) = navigate(AlarmDetail(id))


fun NavGraphBuilder.alarmDetailDestination(){
    composable<AlarmDetail> { backStackEntry ->
        val args = backStackEntry.toRoute<AlarmDetail>()
        AlarmDetailDialog(alarmId = args.id)
    }
}