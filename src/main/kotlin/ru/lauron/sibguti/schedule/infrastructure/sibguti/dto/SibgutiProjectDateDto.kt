package ru.lauron.sibguti.schedule.infrastructure.sibguti.dto

import kotlinx.datetime.LocalDate
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SibgutiProjectDateDto(
    @SerialName("ID")
    val id: String,

    @SerialName("GROUP_UUID")
    val groupUuid: String? = null,

    @SerialName("START_DATE")
    val startDate: LocalDate,

    @SerialName("END_DATE")
    val endDate: LocalDate
)