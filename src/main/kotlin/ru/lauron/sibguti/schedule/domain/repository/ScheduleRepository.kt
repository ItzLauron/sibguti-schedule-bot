package ru.lauron.sibguti.schedule.domain.repository

import kotlinx.datetime.LocalDate
import ru.lauron.sibguti.schedule.domain.model.Schedule

interface ScheduleRepository {

    suspend fun find(group: String): Result<Schedule?>

    suspend fun save(
        group: String,
        schedule: Schedule
    ): Result<Unit>
}