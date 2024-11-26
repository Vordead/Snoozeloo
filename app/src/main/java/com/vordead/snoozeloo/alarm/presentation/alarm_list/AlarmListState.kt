package com.vordead.snoozeloo.alarm.presentation.alarm_list

import androidx.compose.runtime.Immutable
import com.vordead.snoozeloo.alarm.presentation.models.AlarmUi

sealed class AlarmListUiState {
    object Loading : AlarmListUiState()
    data class Success(val alarms: List<AlarmUi>) : AlarmListUiState()
    data class Error(val message: String) : AlarmListUiState()
}

@Immutable
data class AlarmListState(
    val uiState: AlarmListUiState = AlarmListUiState.Loading
)