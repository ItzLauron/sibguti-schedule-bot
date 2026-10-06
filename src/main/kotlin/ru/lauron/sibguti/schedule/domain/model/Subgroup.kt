package ru.lauron.sibguti.schedule.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Subgroup(
    val name: String?,
    val teachers: List<String> = emptyList(),
    val classroom: String?,
    val project: Project? = null
)