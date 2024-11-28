package com.vordead.snoozeloo.alarm.presentation.ringing

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vordead.snoozeloo.alarm.domain.AlarmDataSource
import com.vordead.snoozeloo.alarm.presentation.ManageAlarmUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RingingScreenViewModel @Inject constructor(
    private val localAlarmDataSource: AlarmDataSource,
    private val manageAlarmUseCase: ManageAlarmUseCase
) : ViewModel() {

    fun turnOffAlarm(alarmId: Int) {
        viewModelScope.launch {
            manageAlarmUseCase.unscheduleAlarm(alarmId)
            val alarm = localAlarmDataSource.getAlarmById(alarmId)
            if (alarm != null) {
                val updatedAlarm = alarm.copy(isEnabled = false)
                localAlarmDataSource.upsertAlarm(updatedAlarm)
            }
        }
    }
}