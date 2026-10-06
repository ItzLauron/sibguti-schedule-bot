package ru.lauron.sibguti.schedule.infrastructure.sibguti.dto

import kotlinx.datetime.LocalDate
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SibgutiSubgroupDto(
    @SerialName("DISCIPLINE")
    val discipline: String,

    @SerialName("TYPE_LESSON")
    val lessonType: String,

    @SerialName("TEACHER")
    val teachers: List<String> = emptyList(),

    @SerialName("CLASSROOM")
    val classroom: String? = null,

    @SerialName("SUBGROUP")
    val subgroup: String? = null,

    @SerialName("DATE_START_PROJECT")
    val projectStartDate: LocalDate? = null,

    @SerialName("DATE_END_PROJECT")
    val projectEndDate: LocalDate? = null,

    @SerialName("PROJECT_DATES")
    val projectDates: List<SibgutiProjectDateDto> = emptyList()
)