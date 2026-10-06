package ru.lauron.sibguti.schedule.infrastructure.sibguti

import ru.lauron.sibguti.schedule.domain.model.Schedule
import ru.lauron.sibguti.schedule.infrastructure.sibguti.extractor.SibgutiScheduleDataExtractor
import ru.lauron.sibguti.schedule.infrastructure.sibguti.mapper.SibgutiScheduleMapper
import ru.lauron.sibguti.schedule.infrastructure.sibguti.parser.SibgutiScheduleParser

class SibgutiScheduleSource(
    private val client: SibgutiClient,
    private val extractor: SibgutiScheduleDataExtractor,
    private val parser: SibgutiScheduleParser,
    private val mapper: SibgutiScheduleMapper
) {

    suspend fun fetch(group: String): Result<Schedule> {
        return client
            .getSchedule(group)
            .mapCatching { extractor.extract(it).getOrThrow() }
            .mapCatching { parser.parse(it).getOrThrow() }
            .mapCatching { mapper.map(it).getOrThrow() }
    }
}