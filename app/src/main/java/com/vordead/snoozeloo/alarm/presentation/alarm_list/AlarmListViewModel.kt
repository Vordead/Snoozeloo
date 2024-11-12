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
            _alarmListState.update {
                it.copy(
                    alarms = it.alarms.map { alarm ->
                        if (alarm.id == alarmId) {
                            alarm.copy(isEnabled = isChecked)
                        } else {
                            alarm
                        }
                    }
                )
            }
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
                        ),
                        AlarmListItemUi(
                            id = "2",
                            title = "Alarm 2",
                            time = "11:00",
                            period = "AM",
                            remainingTime = "1h",
                            isEnabled = true
                        ),
                        AlarmListItemUi(
                            id = "3",
                            title = "Alarm 3",
                            time = "12:00",
                            period = "PM",
                            remainingTime = "2h",
                            isEnabled = false
                        ),
                        AlarmListItemUi(
                            id = "4",
                            title = "Alarm 4",
                            time = "01:00",
                            period = "PM",
                            remainingTime = "3h",
                            isEnabled = true
                        ),
                        AlarmListItemUi(
                            id = "5",
                            title = "Alarm 5",
                            time = "02:00",
                            period = "PM",
                            remainingTime = "4h",
                            isEnabled = false
                        ),
                        AlarmListItemUi(
                            id = "6",
                            title = "Alarm 6",
                            time = "03:00",
                            period = "PM",
                            remainingTime = "5h",
                            isEnabled = true
                        ),
                    )
                )
            }
        }
    }

}