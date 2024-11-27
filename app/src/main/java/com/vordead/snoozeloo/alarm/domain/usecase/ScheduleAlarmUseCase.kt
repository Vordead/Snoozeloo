package com.vordead.snoozeloo.alarm.domain.usecase

import android.content.Context
import com.vordead.snoozeloo.alarm.domain.AlarmDataSource
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class ScheduleAlarmUseCase @Inject constructor(
    @ApplicationContext private val context: Context,
    private val alarmDataSource: AlarmDataSource
) {
    fun execute(){

    }
}