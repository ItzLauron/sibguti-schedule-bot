package ru.lauron.sibguti.schedule.infrastructure.sibguti.extractor

data class ExtractedScheduleData(
    val days: List<String>,
    val factDays: Map<Int, String>,
    val examDays: Map<Int, String>
)