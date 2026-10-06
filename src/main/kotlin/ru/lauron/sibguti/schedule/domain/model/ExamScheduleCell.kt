package ru.lauron.sibguti.schedule.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class ExamScheduleCell(
    val exams: List<Exam> = emptyList()
)