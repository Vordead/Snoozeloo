package com.vordead.snoozeloo.alarm.presentation.alarm_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vordead.snoozeloo.alarm.domain.AlarmDataSource
import com.vordead.snoozeloo.alarm.presentation.ManageAlarmUseCase
import com.vordead.snoozeloo.alarm.presentation.models.toAlarm
import com.vordead.snoozeloo.alarm.presentation.models.toAlarmUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.Calendar
import javax.inject.Inject

@HiltViewModel
class AlarmListViewModel @Inject constructor(
    private val localAlarmDataSource: AlarmDataSource,
    private val manageAlarmUseCase: ManageAlarmUseCase

) : ViewModel() {

    private val _alarmListEvents = Channel<AlarmListEvent>()
    val alarmListEvents = _alarmListEvents.receiveAsFlow()

    private val _alarmListState = MutableStateFlow(AlarmListState())

    @OptIn(ExperimentalCoroutinesApi::class)
    val state = _alarmListState
        .flatMapLatest {
            localAlarmDataSource.getAllAlarms()
                .map { alarms ->
                    AlarmListState(uiState = AlarmListUiState.Success(alarms.map { it.toAlarmUi() }))
                }
                .catch { e ->
                    emit(AlarmListState(uiState = AlarmListUiState.Error(e.message ?: "Unknown error")))
                }
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5_000),
            AlarmListState(AlarmListUiState.Loading)
        )

    fun onAlarmSwitchClick(alarmId: Int, isChecked: Boolean) {
        viewModelScope.launch {
            val currentState = state.value.uiState
            if (currentState is AlarmListUiState.Success) {
                val updatedAlarm = currentState.alarms.find { it.id == alarmId }?.copy(isEnabled = isChecked)
                if (updatedAlarm != null) {
                    localAlarmDataSource.upsertAlarm(updatedAlarm.toAlarm())
                    _alarmListState.update {
                        it.copy(
                            uiState = AlarmListUiState.Success(
                                currentState.alarms.map { alarm ->
                                    if (alarm.id == alarmId) {
                                        alarm.copy(isEnabled = isChecked)
                                    } else {
                                        alarm
                                    }
                                }
                            )
                        )
                    }
                    if (isChecked) {
                        val triggerTime = Calendar.getInstance().apply {
                            set(Calendar.HOUR_OF_DAY, updatedAlarm.hour)
                            set(Calendar.MINUTE, updatedAlarm.minute)
                            set(Calendar.SECOND, 0)
                        }
                        manageAlarmUseCase.scheduleAlarm(alarmId, triggerTime)
                    } else {
                        manageAlarmUseCase.unscheduleAlarm(alarmId)
                    }
                }
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
                    _alarmListEvents.send(AlarmListEvent.NavigateToAlarmDetail())
                }

                is AlarmListAction.onAlarmClick -> {
                    _alarmListEvents.send(AlarmListEvent.NavigateToAlarmDetail(action.alarmId))
                }
            }
        }
    }
}