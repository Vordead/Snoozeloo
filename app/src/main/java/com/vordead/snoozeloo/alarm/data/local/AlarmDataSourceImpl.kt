package com.vordead.snoozeloo.alarm.data.local

import com.vordead.snoozeloo.alarm.domain.AlarmDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AlarmDataSourceImpl @Inject constructor(
    private val alarmDao: AlarmDao
) : AlarmDataSource {
    override suspend fun insert(alarmEntity: AlarmEntity) {
        alarmDao.insert(alarmEntity)
    }

    override suspend fun update(alarmEntity: AlarmEntity) {
        alarmDao.update(alarmEntity)
    }

    override suspend fun delete(alarmEntity: AlarmEntity) {
        alarmDao.delete(alarmEntity)
    }

    override suspend fun getAlarmById(id: Int): AlarmEntity? {
        return alarmDao.getAlarmById(id)
    }

    override fun getAllAlarms(): Flow<List<AlarmEntity>> {
        return alarmDao.getAllAlarms()
    }

    override suspend fun deleteById(id: Int) {
        alarmDao.deleteById(id)
    }
}