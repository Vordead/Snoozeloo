package com.vordead.snoozeloo.alarm.presentation.alarm_list.navigation

import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.vordead.snoozeloo.alarm.presentation.alarm_list.AlarmListAction
import com.vordead.snoozeloo.alarm.presentation.alarm_list.AlarmListScreen
import com.vordead.snoozeloo.alarm.presentation.alarm_list.AlarmListViewModel
import kotlinx.serialization.Serializable

@Serializable
data object AlarmListNavigation

fun NavGraphBuilder.alarmListDestination(
    onNavigateToAlarmDetail: (alarmId: String) -> Unit
) {
    composable<AlarmListNavigation> {
        val alarmListViewModel : AlarmListViewModel = hiltViewModel()
        val state by alarmListViewModel.state.collectAsStateWithLifecycle()
        AlarmListScreen(
            state = state,
            onAction = { action ->
                val shouldNavigate = alarmListViewModel.onAction(action)
                if (shouldNavigate) {
                    when (action) {
                        AlarmListAction.onCreateAlarmClick -> onNavigateToAlarmDetail("")
                        is AlarmListAction.onAlarmClick -> onNavigateToAlarmDetail(action.alarmId)
                        else -> {}
                    }
                }
            }
        )
    }
}