package ru.lauron.sibguti.schedule.domain.model

import kotlinx.datetime.LocalDate
import kotlinx.serialization.Serializable

@Serializable
data class ProjectDate(
    val id: String,
    val groupUuid: String?,
    val startDate: LocalDate,
    val endDate: LocalDate
)