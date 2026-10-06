package ru.lauron.sibguti.schedule.infrastructure.sibguti

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText

class SibgutiClient(private val httpClient: HttpClient) {
    suspend fun getSchedule(group: String): Result<String> {
        return runCatching {
            httpClient.get("https://sibsutis.ru/students/schedule/?type=student&group=$group").bodyAsText()
        }
    }
}