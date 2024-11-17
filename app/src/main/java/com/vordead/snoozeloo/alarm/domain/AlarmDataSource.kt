package com.vordead.snoozeloo.alarm.domain

import com.vordead.snoozeloo.alarm.data.local.AlarmEntity
import kotlinx.coroutines.flow.Flow

interface AlarmDataSource {
    suspend fun insert(alarmEntity: AlarmEntity)
    suspend fun update(alarmEntity: AlarmEntity)
    suspend fun delete(alarmEntity: AlarmEntity)
    suspend fun getAlarmById(id: Int): AlarmEntity?
    fun getAllAlarms(): Flow<List<AlarmEntity>>
    suspend fun deleteById(id: Int)
}