package com.vordead.snoozeloo.alarm.presentation.ringing.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navDeepLink
import androidx.navigation.toRoute
import com.vordead.snoozeloo.DEEPLINK_DOMAIN
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
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(text = "Ringing Screen for Alarm $alarmId")
        }
    }
}