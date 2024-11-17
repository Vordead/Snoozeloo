package com.vordead.snoozeloo.alarm.di

import com.vordead.snoozeloo.alarm.data.local.AlarmDataSourceImpl
import com.vordead.snoozeloo.alarm.domain.AlarmDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AlarmModule {

    @Binds
    abstract fun bindAlarmDataSource(alarmDataSourceImpl: AlarmDataSourceImpl): AlarmDataSource
}