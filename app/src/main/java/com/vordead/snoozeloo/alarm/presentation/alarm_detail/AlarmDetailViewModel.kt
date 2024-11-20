package com.vordead.snoozeloo.alarm.presentation.alarm_detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vordead.snoozeloo.alarm.data.local.LocalAlarmDataSource
import com.vordead.snoozeloo.alarm.presentation.models.AlarmUi
import com.vordead.snoozeloo.alarm.presentation.models.toAlarm
import com.vordead.snoozeloo.alarm.presentation.models.toAlarmUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AlarmDetailViewModel @Inject constructor(
    private val localAlarmDataSource: LocalAlarmDataSource
) : ViewModel() {

    private val _alarmDetailEvents = Channel<AlarmDetailEvent>()
    val alarmDetailEvents = _alarmDetailEvents.receiveAsFlow()


    private val _uiState = MutableStateFlow(AlarmDetailState())
    val uiState = _uiState.stateIn(
        viewModelScope,
        initialValue = AlarmDetailState(),
        started = SharingStarted.WhileSubscribed(5_000)
    )


    fun loadAlarm(alarmId: String?) {
        viewModelScope.launch {
            val alarm = alarmId?.let { localAlarmDataSource.getAlarmById(it.toInt()) }?.toAlarmUi()
            _uiState.update {
                it.copy(
                    alarm = alarm,
                    hourField = alarm?.time?.split(":")?.get(0) ?: "",
                    minuteField = alarm?.time?.split(":")?.get(1) ?: "",
                    alarmName = alarm?.title ?: ""
                )
            }
        }
    }

    fun onTimeChange(hour: String? = null, minute: String? = null) {
        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    hourField = hour ?: it.hourField,
                    minuteField = minute ?: it.minuteField
                )
            }
        }
    }

    fun onAlarmNameChange(alarmName: String) {
        _uiState.update {
            it.copy(
                alarmName = alarmName,
                showAlarmNameDialog = false
            )
        }
    }


    fun saveAlarm() {
        viewModelScope.launch {
            val currentState = _uiState.value
            if (currentState.isAlarmValid) {
                val alarm = currentState.alarm?.copy(
                    title = currentState.alarmName,
                    time = "${currentState.hourField}:${currentState.minuteField}"
                ) ?: AlarmUi(
                    title = currentState.alarmName,
                    time = "${currentState.hourField}:${currentState.minuteField}",
                    period = "", // Set default or handle accordingly
                    isEnabled = true,
                    remainingTime = ""
                )
                localAlarmDataSource.upsertAlarm(alarm.toAlarm())
                _alarmDetailEvents.send(AlarmDetailEvent.NavigateBack)
            }
        }
    }


    fun showAlarmDialog(shouldShow: Boolean) {
        viewModelScope.launch {
            _uiState.update {
                it.copy(showAlarmNameDialog = shouldShow)
            }
        }
    }

    fun onAction(action: AlarmDetailAction) {
        viewModelScope.launch {
            when (action) {
                is AlarmDetailAction.OnAlarmNameClick -> {
                    showAlarmDialog(true)
                }

                AlarmDetailAction.OnBackClick -> {
                    _alarmDetailEvents.send(AlarmDetailEvent.NavigateBack)
                }

                AlarmDetailAction.OnDismissAlarmNameDialog -> {
                    showAlarmDialog(false)
                }

                is AlarmDetailAction.OnSaveAlarmName -> {
                    onAlarmNameChange(action.alarmName ?: "")
                }

                AlarmDetailAction.OnSaveClick -> {
                    saveAlarm()
                }

                is AlarmDetailAction.OnTimeChange -> {
                    onTimeChange(action.hour, action.minute)
                }
            }
        }
    }


}