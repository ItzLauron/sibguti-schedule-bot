package ru.lauron.sibguti.schedule.infrastructure.sibguti.extractor

class ScheduleExtractionException(
    message: String,
    cause: Throwable? = null
) : RuntimeException(message, cause)