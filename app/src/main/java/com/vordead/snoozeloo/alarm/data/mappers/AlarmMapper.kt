package com.vordead.snoozeloo.alarm.data.mappers

import com.vordead.snoozeloo.alarm.data.local.AlarmEntity
import com.vordead.snoozeloo.alarm.domain.models.Alarm

fun AlarmEntity.toDomainModel(): Alarm {
    return Alarm(
        id = this.id,
        time = this.time,
        isEnabled = this.isEnabled,
        repeatDays = this.repeatDays,
        alarmName = this.alarmName,
        ringtone = this.ringtone,
        volume = this.volume,
        vibrate = this.vibrate
    )
}

fun Alarm.toEntityModel(): AlarmEntity {
    return AlarmEntity(
        id = this.id,
        time = this.time,
        isEnabled = this.isEnabled,
        repeatDays = this.repeatDays,
        alarmName = this.alarmName,
        ringtone = this.ringtone,
        volume = this.volume,
        vibrate = this.vibrate
    )
}