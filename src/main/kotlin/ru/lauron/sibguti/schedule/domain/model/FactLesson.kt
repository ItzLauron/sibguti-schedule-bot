package ru.lauron.sibguti.schedule.domain.model

import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime
import kotlinx.serialization.Serializable

@Serializable
data class FactLesson(
    val dateBegin: LocalTime,
    val discipline: String,
    val type: String,
    val teachers: List<String> = emptyList(),
    val classroom: String?,
    val groups: List<String> = emptyList(),
    val subgroup: String?
)