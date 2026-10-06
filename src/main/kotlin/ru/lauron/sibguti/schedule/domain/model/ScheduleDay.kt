package ru.lauron.sibguti.schedule.domain.model

import kotlinx.datetime.LocalDate
import kotlinx.serialization.Serializable

@Serializable
data class ScheduleDay(
    val cells: List<ScheduleCell> = emptyList()
)