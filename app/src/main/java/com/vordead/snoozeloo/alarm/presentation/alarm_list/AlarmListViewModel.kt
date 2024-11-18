package com.vordead.snoozeloo.alarm.presentation.alarm_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vordead.snoozeloo.alarm.data.local.LocalAlarmDataSource
import com.vordead.snoozeloo.alarm.presentation.models.toAlarmUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AlarmListViewModel @Inject constructor(
    private val localAlarmDataSource: LocalAlarmDataSource
) : ViewModel() {

    private val _alarmListEvents = Channel<AlarmListEvent>()
    val alarmListEvents = _alarmListEvents.receiveAsFlow()

    private val _alarmListState = MutableStateFlow(AlarmListState())

    @OptIn(ExperimentalCoroutinesApi::class)
    val state = _alarmListState
        .flatMapLatest {
            localAlarmDataSource.getAllAlarms()
                .map { alarms ->
                    AlarmListState(alarms = alarms.map { it.toAlarmUi() })
                }
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5_000),
            AlarmListState()
        )

    fun onAlarmSwitchClick(alarmId: String, isChecked: Boolean) {
        viewModelScope.launch {
            _alarmListState.update { currentState ->
                currentState.copy(
                    alarms = currentState.alarms.map { alarm ->
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


    fun onAction(action: AlarmListAction) {
        viewModelScope.launch {
            when (action) {
                is AlarmListAction.onAlarmSwitchClick -> {
                    onAlarmSwitchClick(action.alarmId, action.isChecked)
                }

                AlarmListAction.onCreateAlarmClick -> {
                    _alarmListEvents.send(AlarmListEvent.NavigateToAlarmDetail(""))
                }

                is AlarmListAction.onAlarmClick -> {
                    _alarmListEvents.send(AlarmListEvent.NavigateToAlarmDetail(action.alarmId))
                }
            }
        }
    }

}