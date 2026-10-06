package ru.lauron.sibguti.schedule.domain.repository

import kotlinx.serialization.json.Json
import ru.lauron.sibguti.schedule.domain.model.Schedule
import java.nio.file.Path
import kotlin.io.path.exists
import kotlin.io.path.readText
import kotlin.io.path.writeText

class JsonScheduleRepository(
    private val json: Json,
    private val file: Path
) : ScheduleRepository {

    override suspend fun find(
        group: String
    ): Result<Schedule?> = runCatching {
        if (!file.exists()) {
            return@runCatching null
        }

        val storage = json.decodeFromString<Map<String, Schedule>>(
            file.readText()
        )

        storage[group]
    }

    override suspend fun save(
        group: String,
        schedule: Schedule
    ): Result<Unit> = runCatching {
        val storage = if (file.exists()) {
            json.decodeFromString<Map<String, Schedule>>(
                file.readText()
            ).toMutableMap()
        } else {
            mutableMapOf()
        }

        storage[group] = schedule

        file.writeText(
            json.encodeToString(storage)
        )
    }
}