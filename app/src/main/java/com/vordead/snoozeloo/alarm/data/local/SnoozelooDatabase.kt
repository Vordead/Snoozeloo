package com.vordead.snoozeloo.alarm.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [AlarmEntity::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class SnoozelooDatabase : RoomDatabase() {
    abstract fun alarmDao(): AlarmDao
}