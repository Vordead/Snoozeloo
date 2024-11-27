package com.vordead.snoozeloo.alarm.presentation.ringing.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navDeepLink
import androidx.navigation.toRoute
import com.vordead.snoozeloo.DEEPLINK_DOMAIN
import com.vordead.snoozeloo.alarm.presentation.ringing.RingingScreen
import kotlinx.serialization.Serializable

@Serializable
data class AlarmRingingDestination(
    val alarmId: Int
)

fun NavGraphBuilder.alarmRingingDestination(
) {
    composable<AlarmRingingDestination>(
        deepLinks = listOf(
            navDeepLink<AlarmRingingDestination>(
                basePath = "https://$DEEPLINK_DOMAIN"
            )
        )
    ) { backStackEntry ->
        val args = backStackEntry.toRoute<AlarmRingingDestination>()
        val alarmId = args.alarmId
        RingingScreen(alarmId = alarmId, onTurnOffClick = {

        })
    }
}