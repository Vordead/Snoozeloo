package com.vordead.snoozeloo.alarm.presentation.alarm_detail.navigation

import androidx.compose.animation.AnimatedContentTransitionScope.SlideDirection.Companion.Down
import androidx.compose.animation.AnimatedContentTransitionScope.SlideDirection.Companion.Up
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.vordead.snoozeloo.alarm.presentation.alarm_detail.AlarmDetailAction
import com.vordead.snoozeloo.alarm.presentation.alarm_detail.AlarmDetailScreen
import com.vordead.snoozeloo.alarm.presentation.alarm_detail.AlarmDetailViewModel
import kotlinx.serialization.Serializable

@Serializable
data class AlarmDetail(val id: String? = null)

fun NavController.navigateToAlarmDetail(id: String) = navigate(AlarmDetail(id))


fun NavGraphBuilder.alarmDetailDestination(
    onNavigateBack: () -> Unit,
    onNavigateToAlarmDialog : () -> Unit,
) {
    composable<AlarmDetail>(
        enterTransition = {
            slideIntoContainer(Up)
        },
        exitTransition = {
            slideOutOfContainer(Down)
        }
    ) { backStackEntry ->
        val args = backStackEntry.toRoute<AlarmDetail>()
        val vm: AlarmDetailViewModel = hiltViewModel()
        val uiState = vm.uiState.collectAsStateWithLifecycle()
        AlarmDetailScreen(
            alarmId = args.id,
            state = uiState.value,
            onAction = {
                when (it) {
                    is AlarmDetailAction.onSaveClick -> {
                        onNavigateBack()
                    }

                    is AlarmDetailAction.onBackClick -> {
                        onNavigateBack()
                    }

                    is AlarmDetailAction.onAlarmNameClick -> {
                        // Handle alarm name click
                    }

                    is AlarmDetailAction.onTimeChange -> {
                        // Handle time change
                    }
                }
            }
        )
    }
}