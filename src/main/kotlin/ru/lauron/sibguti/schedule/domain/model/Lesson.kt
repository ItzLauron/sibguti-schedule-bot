package ru.lauron.sibguti.schedule.domain.model

import kotlinx.datetime.LocalTime
import kotlinx.serialization.Serializable

@Serializable
data class Lesson(
    val discipline: String,
    val type: String,
    val subgroups: List<Subgroup> = emptyList()
)