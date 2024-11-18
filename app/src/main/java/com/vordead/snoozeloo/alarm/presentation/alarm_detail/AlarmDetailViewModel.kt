package com.vordead.snoozeloo.alarm.presentation.alarm_detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vordead.snoozeloo.alarm.data.local.LocalAlarmDataSource
import com.vordead.snoozeloo.alarm.presentation.models.AlarmUi
import com.vordead.snoozeloo.alarm.presentation.models.toAlarm
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

    fun onAlarmNameChange(alarmName: String) {
        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    alarm = it.alarm?.copy(title = alarmName),
                    showAlarmNameDialog = false
                )
            }
        }
    }

    fun createOrUpdateAlarm(alarm: AlarmUi) {
        viewModelScope.launch {
            localAlarmDataSource.upsertAlarm(alarm.toAlarm())
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
                    createOrUpdateAlarm(_uiState.value.alarm ?: return@launch)
                    _alarmDetailEvents.send(AlarmDetailEvent.NavigateBack)
                }

                is AlarmDetailAction.OnTimeChange -> {
                    // Handle time change
                }
            }
        }
    }


}