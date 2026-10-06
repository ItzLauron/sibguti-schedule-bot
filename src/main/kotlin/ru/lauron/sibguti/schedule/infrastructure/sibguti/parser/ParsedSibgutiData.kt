package ru.lauron.sibguti.schedule.infrastructure.sibguti.parser

import ru.lauron.sibguti.schedule.infrastructure.sibguti.dto.SibgutiExamScheduleDayDto
import ru.lauron.sibguti.schedule.infrastructure.sibguti.dto.SibgutiFactScheduleDayDto
import ru.lauron.sibguti.schedule.infrastructure.sibguti.dto.SibgutiScheduleDto

data class ParsedSibgutiData(
    val days: List<SibgutiScheduleDto>,
    val factDays: Map<Int, SibgutiFactScheduleDayDto>,
    val examDays: Map<Int, SibgutiExamScheduleDayDto>
)