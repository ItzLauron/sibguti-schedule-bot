package ru.lauron.sibguti.schedule.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class FactScheduleCell(
    val lessons: List<FactLesson> = emptyList()
)