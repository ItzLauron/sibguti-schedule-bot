package ru.lauron.sibguti.schedule.domain.model

import kotlinx.datetime.LocalTime
import kotlinx.serialization.Serializable

@Serializable
data class ScheduleCell(
    val startTime: LocalTime,
    val endTime: LocalTime,
    val lessons: List<Lesson> = emptyList()
)