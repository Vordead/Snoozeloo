package com.vordead.snoozeloo.alarm.di

import android.content.Context
import androidx.room.Room
import com.vordead.snoozeloo.alarm.data.local.LocalAlarmDataSource
import com.vordead.snoozeloo.alarm.data.local.SnoozelooDatabase
import com.vordead.snoozeloo.alarm.domain.AlarmDataSource
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import kotlin.jvm.java

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Binds
    abstract fun bindAlarmDataSource(localAlarmDataSource: LocalAlarmDataSource): AlarmDataSource
}

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): SnoozelooDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            SnoozelooDatabase::class.java,
            "snoozeloo_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideAlarmDao(snoozelooDatabase: SnoozelooDatabase) = snoozelooDatabase.alarmDao()
}