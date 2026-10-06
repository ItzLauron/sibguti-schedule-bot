package ru.lauron.sibguti.schedule.infrastructure.sibguti.parser

import kotlinx.serialization.json.Json
import ru.lauron.sibguti.schedule.infrastructure.sibguti.dto.SibgutiExamScheduleDayDto
import ru.lauron.sibguti.schedule.infrastructure.sibguti.dto.SibgutiFactScheduleDayDto
import ru.lauron.sibguti.schedule.infrastructure.sibguti.dto.SibgutiScheduleDto
import ru.lauron.sibguti.schedule.infrastructure.sibguti.extractor.ExtractedScheduleData

class SibgutiScheduleParser(
    private val json: Json
) : ScheduleParser {

    override fun parse(
        data: ExtractedScheduleData
    ): Result<ParsedSibgutiData> = runCatching {
        ParsedSibgutiData(
            days = data.days.map { value ->
                json.decodeFromString<SibgutiScheduleDto>(value)
            },

            factDays = data.factDays.mapValues { (_, value) ->
                json.decodeFromString<SibgutiFactScheduleDayDto>(value)
            },

            examDays = data.examDays.mapValues { (_, value) ->
                json.decodeFromString<SibgutiExamScheduleDayDto>(value)
            }
        )
    }
}