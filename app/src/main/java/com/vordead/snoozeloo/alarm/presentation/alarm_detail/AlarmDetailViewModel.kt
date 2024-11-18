package com.vordead.snoozeloo.alarm.presentation.alarm_detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vordead.snoozeloo.alarm.data.local.LocalAlarmDataSource
import com.vordead.snoozeloo.alarm.domain.models.Alarm
import com.vordead.snoozeloo.alarm.presentation.models.AlarmUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AlarmDetailViewModel @Inject constructor(
    private val localAlarmDataSource: LocalAlarmDataSource
) : ViewModel() {
    private val _uiState = MutableStateFlow(AlarmDetailState())
    val uiState = _uiState.stateIn(
        viewModelScope,
        initialValue = AlarmDetailState(),
        started = SharingStarted.WhileSubscribed(5_000)
    )

    init {
        viewModelScope.launch {
            createOrUpdateAlarm(
                AlarmUi(
                    id = "1",
                    title = "Alarm",
                    time = "12:00",
                    period = "Mon, Tue, Wed, Thu, Fri",
                    isEnabled = true,
                    remainingTime = "Remaining time"
                )
            )
        }
    }

    fun onAlarmNameChange(alarmName: String) {
        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    alarmName = alarmName,
                    showAlarmNameDialog = false
                )
            }
        }
    }

    fun createOrUpdateAlarm(alarm: AlarmUi) {
        viewModelScope.launch {
            localAlarmDataSource.upsertAlarm(
                Alarm(
                    id = alarm.id.toInt(),
                    alarmName = "alarm.title",
                    time = "alarm.time",
                    repeatDays = alarm.period.split(", "),
                    isEnabled = alarm.isEnabled
                )
            )
        }
    }


    fun showAlarmDialog(shouldShow: Boolean) {
        val ss = "hello"
        val res = Result.success(ss)
        res.onSuccess { value -> println(value) }
        viewModelScope.launch {
            _uiState.update {
                it.copy(showAlarmNameDialog = shouldShow)
            }
        }
    }


}