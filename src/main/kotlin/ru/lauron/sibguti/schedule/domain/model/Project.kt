package ru.lauron.sibguti.schedule.domain.model

import kotlinx.datetime.LocalDate
import kotlinx.serialization.Serializable

@Serializable
data class Project(
    val startDate: LocalDate? = null,
    val endDate: LocalDate? = null,
    val dates: List<ProjectDate> = emptyList()
)