package ru.lauron.sibguti.schedule.infrastructure.sibguti.extractor

class SibgutiScheduleDataExtractor : ScheduleDataExtractor<String> {
    override fun extract(source: String): Result<ExtractedScheduleData> {
        return runCatching {
            ExtractedScheduleData(
                days = extractDays(source),
                factDays = extractFactDays(source),
                examDays = extractExamDays(source)
            )
        }
    }

    private fun extractDays(source: String): List<String> {
        val result = ArrayList<String>(14)

        val prefix = """{"Date":"0001-01-"""
        val suffix = """","DayOfWeek":null,"ScheduleCell":"""

        var searchFrom = 0

        for (day in 1..14) {
            val found = source.indexOf(prefix, searchFrom)

            if (found == -1) {
                throw ScheduleExtractionException(
                    "Could not find regular schedule day: $day"
                )
            }

            val dateStart = found + prefix.length

            val expectedDay = if (day < 10) {
                "0$day"
            } else {
                day.toString()
            }

            if (!source.regionMatches(dateStart, expectedDay, 0, 2)) {
                throw ScheduleExtractionException(
                    "Unexpected date for regular schedule day: $day"
                )
            }

            val scheduleCellStart =
                source.indexOf(suffix, dateStart + 2)

            if (scheduleCellStart == -1) {
                throw ScheduleExtractionException(
                    "Could not find ScheduleCell for regular schedule day: $day"
                )
            }

            val contentStart = scheduleCellStart + suffix.length
            val contentEnd = source.indexOf("}'\n", contentStart)

            if (contentEnd == -1) {
                throw ScheduleExtractionException(
                    "Could not find end of regular schedule day: $day"
                )
            }

            result.add(source.substring(contentStart, contentEnd))

            searchFrom = contentEnd + 3
        }

        return result
    }

    private fun extractFactDays(source: String): Map<Int, String> {
        val result = HashMap<Int, String>()

        val prefix = "fact_schedule_days["
        val valuePrefix = "= '"
        val scheduleCell = "\"ScheduleCell\":"

        var searchFrom = 0

        while (true) {
            val prefixStart = source.indexOf(prefix, searchFrom)

            if (prefixStart == -1) {
                break
            }

            val indexStart = prefixStart + prefix.length
            val indexEnd = source.indexOf(']', indexStart)

            if (indexEnd == -1) {
                throw ScheduleExtractionException(
                    "Invalid fact_schedule_days declaration"
                )
            }

            var day = 0

            for (i in indexStart until indexEnd) {
                val digit = source[i] - '0'

                if (digit !in 0..9) {
                    throw ScheduleExtractionException(
                        "Invalid fact_schedule_days index"
                    )
                }

                day = day * 10 + digit
            }

            val valueStart = source.indexOf(valuePrefix, indexEnd)

            if (valueStart == -1) {
                throw ScheduleExtractionException(
                    "Could not find value of fact_schedule_days[$day]"
                )
            }

            val contentStart = valueStart + valuePrefix.length

            if (source.regionMatches(contentStart, "null", 0, 4)) {
                searchFrom = contentStart + 4
                continue
            }

            val scheduleCellStart =
                source.indexOf(scheduleCell, contentStart)

            if (scheduleCellStart == -1) {
                throw ScheduleExtractionException(
                    "Could not find ScheduleCell in fact_schedule_days[$day]"
                )
            }

            val arrayStart = scheduleCellStart + scheduleCell.length

            val contentEnd = source.indexOf("'\n", arrayStart)

            if (contentEnd == -1) {
                throw ScheduleExtractionException(
                    "Could not find end of fact_schedule_days[$day]"
                )
            }

            result[day] = source.substring(arrayStart, contentEnd)

            searchFrom = contentEnd + 2
        }

        return result
    }

    private fun extractExamDays(source: String): Map<Int, String> {
        TODO()
    }
}