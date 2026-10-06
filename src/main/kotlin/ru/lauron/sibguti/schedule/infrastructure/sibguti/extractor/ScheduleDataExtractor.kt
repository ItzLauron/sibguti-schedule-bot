package ru.lauron.sibguti.schedule.infrastructure.sibguti.extractor

interface ScheduleDataExtractor<T> {
    fun extract(source: T): Result<ExtractedScheduleData>
}