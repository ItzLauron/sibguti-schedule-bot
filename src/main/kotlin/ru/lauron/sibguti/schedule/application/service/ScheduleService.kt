package ru.lauron.sibguti.schedule.application.service

import ru.lauron.sibguti.schedule.domain.model.Schedule
import ru.lauron.sibguti.schedule.domain.repository.ScheduleRepository
import ru.lauron.sibguti.schedule.infrastructure.sibguti.SibgutiScheduleSource

class ScheduleService(
    private val repository: ScheduleRepository,
    private val source: SibgutiScheduleSource
) {

    suspend fun update(group: String): Result<Schedule> {
        return source.fetch(group)
            .mapCatching { schedule ->
                repository.save(group, schedule).getOrThrow()
                schedule
            }
    }

    suspend fun get(group: String): Result<Schedule?> {
        return repository.find(group)
    }
}