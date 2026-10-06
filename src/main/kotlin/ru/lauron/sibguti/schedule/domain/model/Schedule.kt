package ru.lauron.sibguti.schedule.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Schedule(
    val days: List<ScheduleDay> = emptyList(),
    val factDays: Map<Int, FactScheduleDay> = emptyMap(),
    val examDays: Map<Int, Exam> = emptyMap()
)