package com.vordead.snoozeloo.alarm.presentation.ringing.navigation

import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navDeepLink
import androidx.navigation.toRoute
import com.vordead.snoozeloo.DEEPLINK_DOMAIN
import com.vordead.snoozeloo.alarm.presentation.ManageAlarmUseCase
import com.vordead.snoozeloo.alarm.presentation.ManageAlarmUseCaseEntryPoint
import com.vordead.snoozeloo.alarm.presentation.ringing.RingingScreen
import dagger.hilt.android.EntryPointAccessors
import kotlinx.serialization.Serializable
import kotlin.jvm.java

@Serializable
data class AlarmRingingDestination(
    val alarmId: Int
)

fun NavGraphBuilder.alarmRingingDestination(
    onNavigateBack: () -> Unit
) {
    composable<AlarmRingingDestination>(
        deepLinks = listOf(
            navDeepLink<AlarmRingingDestination>(
                basePath = "https://$DEEPLINK_DOMAIN"
            )
        )
    ) { backStackEntry ->

        val context = LocalContext.current
        val entryPoint = EntryPointAccessors.fromApplication(context, ManageAlarmUseCaseEntryPoint::class.java)
        val manageAlarmUseCase = entryPoint.manageAlarmUseCase()

        val args = backStackEntry.toRoute<AlarmRingingDestination>()
        val alarmId = args.alarmId
        RingingScreen(alarmId = alarmId, onTurnOffClick = {
            if (alarmId != null) {
                manageAlarmUseCase.unscheduleAlarm(alarmId)
            }
            onNavigateBack()
        })
    }
}