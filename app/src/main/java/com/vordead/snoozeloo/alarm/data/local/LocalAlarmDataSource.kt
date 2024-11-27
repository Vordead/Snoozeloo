package com.vordead.snoozeloo.alarm.data.local

import com.vordead.snoozeloo.alarm.data.mappers.toDomainModel
import com.vordead.snoozeloo.alarm.data.mappers.toEntityModel
import com.vordead.snoozeloo.alarm.domain.AlarmDataSource
import com.vordead.snoozeloo.alarm.domain.models.Alarm
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LocalAlarmDataSource @Inject constructor(
    private val alarmDao: AlarmDao
) : AlarmDataSource {
    override suspend fun upsertAlarm(alarm: Alarm) : Long {
         return alarmDao.upsert(alarm.toEntityModel())
    }

    override suspend fun delete(alarm: Alarm) {
        alarmDao.delete(alarm.toEntityModel())
    }

    override suspend fun getAlarmById(id: Int): Alarm? {
        return alarmDao.getAlarmById(id)?.toDomainModel()
    }

    override fun getAllAlarms(): Flow<List<Alarm>> {
        return alarmDao.getAllAlarms().map { alarmEntities ->
            alarmEntities.map { it.toDomainModel() }
        }
    }

    override suspend fun deleteById(id: Int) {
        alarmDao.deleteById(id)
    }
}