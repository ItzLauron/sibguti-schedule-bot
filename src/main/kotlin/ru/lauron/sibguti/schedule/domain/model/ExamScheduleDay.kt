package ru.lauron.sibguti.schedule.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class ExamScheduleDay(
    val cells: List<ExamScheduleCell> = emptyList()
)