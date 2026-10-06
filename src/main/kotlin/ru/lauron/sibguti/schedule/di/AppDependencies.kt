package ru.lauron.sibguti.schedule.di

import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import kotlinx.serialization.json.Json
import ru.lauron.sibguti.schedule.application.service.ScheduleService
import ru.lauron.sibguti.schedule.domain.repository.InMemoryScheduleRepository
import ru.lauron.sibguti.schedule.infrastructure.sibguti.SibgutiClient
import ru.lauron.sibguti.schedule.infrastructure.sibguti.SibgutiScheduleSource
import ru.lauron.sibguti.schedule.infrastructure.sibguti.extractor.SibgutiScheduleDataExtractor
import ru.lauron.sibguti.schedule.infrastructure.sibguti.mapper.SibgutiScheduleMapper
import ru.lauron.sibguti.schedule.infrastructure.sibguti.parser.SibgutiScheduleParser

class AppDependencies {

    val json = Json {
        ignoreUnknownKeys = true
    }

    val httpClient = HttpClient(CIO)

    val sibgutiClient = SibgutiClient(
        httpClient = httpClient
    )

    val extractor = SibgutiScheduleDataExtractor()

    val parser = SibgutiScheduleParser(json)

    val mapper = SibgutiScheduleMapper()

    val repository = InMemoryScheduleRepository()

    val source = SibgutiScheduleSource(
        client = sibgutiClient,
        extractor = extractor,
        parser = parser,
        mapper = mapper
    )

    val scheduleService = ScheduleService(
        repository = repository,
        source = source,
    )
}