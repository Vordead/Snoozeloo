package com.vordead.snoozeloo.alarm.presentation.alarm_list.navigation

import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.vordead.snoozeloo.alarm.presentation.alarm_list.AlarmListEvent
import com.vordead.snoozeloo.alarm.presentation.alarm_list.AlarmListScreen
import com.vordead.snoozeloo.alarm.presentation.alarm_list.AlarmListViewModel
import com.vordead.snoozeloo.core.presentation.util.ObserveAsEvents
import kotlinx.serialization.Serializable

@Serializable
data object AlarmListNavigation

fun NavGraphBuilder.alarmListDestination(
    onNavigateToAlarmDetail: (alarmId: Int?) -> Unit
) {
    composable<AlarmListNavigation> {
        val alarmListViewModel: AlarmListViewModel = hiltViewModel()
        val state by alarmListViewModel.state.collectAsStateWithLifecycle()

        ObserveAsEvents(
            events = alarmListViewModel.alarmListEvents
        ) { event ->
            when (event) {
                is AlarmListEvent.NavigateToAlarmDetail -> onNavigateToAlarmDetail(event.alarmId)
            }
        }

        AlarmListScreen(
            state = state,
            onAction = alarmListViewModel::onAction
        )
    }
}