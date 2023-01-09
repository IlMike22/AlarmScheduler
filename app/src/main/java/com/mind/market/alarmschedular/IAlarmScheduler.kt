package com.mind.market.alarmschedular

interface IAlarmScheduler {
    fun schedule(item:AlarmItem)
    fun cancel(item:AlarmItem)
}