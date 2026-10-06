package ru.lauron.sibguti.schedule.domain.model

import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalTime
import kotlinx.serialization.Serializable

@Serializable
data class Exam(
    val date: LocalDate,
    val startTime: LocalTime,
    val endTime: LocalTime,
    val discipline: String,
    val teachers: List<String> = emptyList(),
    val classroom: String? = null
)