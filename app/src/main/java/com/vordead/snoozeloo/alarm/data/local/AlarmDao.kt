package com.vordead.snoozeloo.alarm.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface AlarmDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(alarmEntity: AlarmEntity)

    @Update
    suspend fun update(alarmEntity: AlarmEntity)

    @Delete
    suspend fun delete(alarmEntity: AlarmEntity)

    @Query("SELECT * FROM alarms WHERE id = :id")
    suspend fun getAlarmById(id: Int): AlarmEntity?

    @Query("SELECT * FROM alarms ORDER BY time ASC")
    fun getAllAlarms(): Flow<List<AlarmEntity>>

    @Query("DELETE FROM alarms WHERE id = :id")
    suspend fun deleteById(id: Int)
}