package com.vordead.snoozeloo.alarm.domain

import com.vordead.snoozeloo.alarm.domain.models.Alarm
import kotlinx.coroutines.flow.Flow

interface AlarmDataSource {
    suspend fun upsertAlarm(alarm: Alarm)
    suspend fun delete(alarm: Alarm)
    suspend fun getAlarmById(id: Int): Alarm?
    fun getAllAlarms(): Flow<List<Alarm>>
    suspend fun deleteById(id: Int)
}