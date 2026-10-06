package ru.lauron.sibguti.schedule.domain.model

import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable

@Serializable
data class FactScheduleDay(
    val cells: List<FactScheduleCell> = emptyList()
)