package com.vordead.snoozeloo.alarm.presentation.alarm_detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class AlarmDetailViewModel @Inject constructor() : ViewModel() {
    private val _uiState = MutableStateFlow(AlarmDetailState())
    val uiState = _uiState.stateIn(
        viewModelScope,
        initialValue = AlarmDetailState(),
        started = SharingStarted.WhileSubscribed(5_000)
    )




}