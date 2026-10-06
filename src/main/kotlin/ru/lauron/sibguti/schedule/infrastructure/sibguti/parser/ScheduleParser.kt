package ru.lauron.sibguti.schedule.infrastructure.sibguti.parser

import ru.lauron.sibguti.schedule.infrastructure.sibguti.extractor.ExtractedScheduleData

interface ScheduleParser {
    fun parse(data: ExtractedScheduleData): Result<ParsedSibgutiData>
}