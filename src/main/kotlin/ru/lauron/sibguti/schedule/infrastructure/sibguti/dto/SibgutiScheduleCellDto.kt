package ru.lauron.sibguti.schedule.infrastructure.sibguti.dto

import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SibgutiScheduleCellDto(
    @SerialName("DateBegin")
    val dateBegin: LocalDateTime,

    @SerialName("DateEnd")
    val dateEnd: LocalDateTime,

    @SerialName("Subgroup")
    val subgroups: List<SibgutiSubgroupDto> = emptyList()
)