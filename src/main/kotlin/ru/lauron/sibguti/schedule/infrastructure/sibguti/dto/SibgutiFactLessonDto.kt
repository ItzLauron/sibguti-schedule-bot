package ru.lauron.sibguti.schedule.infrastructure.sibguti.dto

import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SibgutiFactLessonDto(
    @SerialName("DATE_BEGIN")
    val dateBegin: LocalDateTime,

    @SerialName("DISCIPLINE")
    val discipline: String,

    @SerialName("TYPE_LESSON")
    val lessonType: String,

    @SerialName("TEACHER")
    val teachers: List<String> = emptyList(),

    @SerialName("CLASSROOM")
    val classroom: String? = null,

    @SerialName("GROUP")
    val groups: List<String> = emptyList(),

    @SerialName("SUBGROUP")
    val subgroup: String? = null
)