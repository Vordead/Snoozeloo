package com.vordead.snoozeloo.alarm.presentation.alarm_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AlarmListViewModel @Inject constructor() : ViewModel() {
    private val _alarmListState = MutableStateFlow(AlarmListState())
    val state = _alarmListState
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5_000),
            AlarmListState()
        )


    fun onAlarmSwitchClick(alarmId: String, isChecked: Boolean) {
        viewModelScope.launch {

        }
    }

}