package ru.lauron.sibguti.schedule.domain.repository

import ru.lauron.sibguti.schedule.domain.model.Schedule

class InMemoryScheduleRepository : ScheduleRepository {

    private val schedules = mutableMapOf<String, Schedule>()

    override suspend fun find(
        group: String
    ): Result<Schedule?> {
        return Result.success(schedules[group])
    }

    override suspend fun save(
        group: String,
        schedule: Schedule
    ): Result<Unit> {
        schedules[group] = schedule
        return Result.success(Unit)
    }
}