package com.vordead.snoozeloo.alarm.presentation.alarm_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vordead.snoozeloo.alarm.presentation.models.AlarmListItemUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AlarmListViewModel @Inject constructor() : ViewModel() {
    private val _alarmListState = MutableStateFlow(AlarmListState())
    val state = _alarmListState
        .onStart { loadAlarms() }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5_000),
            AlarmListState()
        )


    fun onAlarmSwitchClick(alarmId: String, isChecked: Boolean) {
        viewModelScope.launch {

        }
    }

    private fun loadAlarms() {
        viewModelScope.launch {
            _alarmListState.update {
                it.copy(
                    alarms = listOf(
                        AlarmListItemUi(
                            id = "1",
                            title = "Alarm 1",
                            time = "10:00",
                            period = "AM",
                            remainingTime = "30min",
                            isEnabled = false
                        )
                    )
                )
            }
        }
    }

}