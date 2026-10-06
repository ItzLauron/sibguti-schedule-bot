package ru.lauron.sibguti.schedule.infrastructure.sibguti.mapper

import ru.lauron.sibguti.schedule.domain.model.ExamScheduleDay
import ru.lauron.sibguti.schedule.domain.model.FactLesson
import ru.lauron.sibguti.schedule.domain.model.FactScheduleCell
import ru.lauron.sibguti.schedule.domain.model.FactScheduleDay
import ru.lauron.sibguti.schedule.domain.model.Lesson
import ru.lauron.sibguti.schedule.domain.model.Project
import ru.lauron.sibguti.schedule.domain.model.ProjectDate
import ru.lauron.sibguti.schedule.domain.model.Schedule
import ru.lauron.sibguti.schedule.domain.model.ScheduleCell
import ru.lauron.sibguti.schedule.domain.model.ScheduleDay
import ru.lauron.sibguti.schedule.domain.model.Subgroup
import ru.lauron.sibguti.schedule.infrastructure.sibguti.dto.SibgutiExamScheduleDayDto
import ru.lauron.sibguti.schedule.infrastructure.sibguti.dto.SibgutiFactLessonDto
import ru.lauron.sibguti.schedule.infrastructure.sibguti.dto.SibgutiFactScheduleDayDto
import ru.lauron.sibguti.schedule.infrastructure.sibguti.dto.SibgutiProjectDateDto
import ru.lauron.sibguti.schedule.infrastructure.sibguti.dto.SibgutiScheduleCellDto
import ru.lauron.sibguti.schedule.infrastructure.sibguti.dto.SibgutiScheduleDto
import ru.lauron.sibguti.schedule.infrastructure.sibguti.dto.SibgutiSubgroupDto
import ru.lauron.sibguti.schedule.infrastructure.sibguti.parser.ParsedSibgutiData

class SibgutiScheduleMapper {

    fun map(data: ParsedSibgutiData): Result<Schedule> = runCatching {
        Schedule(
            days = data.days.map(::mapDay),

            factDays = data.factDays.mapValues { (_, day) ->
                mapFactDay(day)
            },

            examDays = data.examDays.mapValues { (_, day) ->
                mapExamDay(day)
            }
        )
    }

    private fun mapDay(
        day: SibgutiScheduleDto
    ): ScheduleDay {
        return ScheduleDay(
            cells = day.map(::mapCell)
        )
    }

    private fun mapCell(
        cell: SibgutiScheduleCellDto
    ): ScheduleCell {
        return ScheduleCell(
            startTime = cell.dateBegin.time,
            endTime = cell.dateEnd.time,
            lessons = mapLessons(cell)
        )
    }

    private fun mapLessons(
        cell: SibgutiScheduleCellDto
    ): List<Lesson> {
        return cell.subgroups
            .groupBy { it.discipline to it.lessonType }
            .values
            .map { subgroups ->
                val first = subgroups.first()

                Lesson(
                    discipline = first.discipline,
                    type = first.lessonType,
                    subgroups = subgroups.map(::mapSubgroup)
                )
            }
    }

    private fun mapSubgroup(
        subgroup: SibgutiSubgroupDto
    ): Subgroup {
        return Subgroup(
            name = subgroup.subgroup,
            teachers = subgroup.teachers,
            classroom = subgroup.classroom,
            project = mapProject(subgroup)
        )
    }

    private fun mapProject(
        subgroup: SibgutiSubgroupDto
    ): Project? {
        if (
            subgroup.projectStartDate == null &&
            subgroup.projectEndDate == null &&
            subgroup.projectDates.isEmpty()
        ) {
            return null
        }

        return Project(
            startDate = subgroup.projectStartDate,
            endDate = subgroup.projectEndDate,
            dates = subgroup.projectDates.map(::mapProjectDate)
        )
    }

    private fun mapProjectDate(
        date: SibgutiProjectDateDto
    ): ProjectDate {
        return ProjectDate(
            id = date.id,
            groupUuid = date.groupUuid,
            startDate = date.startDate,
            endDate = date.endDate
        )
    }

    private fun mapFactDay(
        day: SibgutiFactScheduleDayDto
    ): FactScheduleDay {
        return FactScheduleDay(
            cells = day.map(::mapFactCell)
        )
    }

    private fun mapFactCell(
        lessons: List<SibgutiFactLessonDto>
    ): FactScheduleCell {
        return FactScheduleCell(
            lessons = lessons.map(::mapFactLesson)
        )
    }

    private fun mapFactLesson(
        lesson: SibgutiFactLessonDto
    ): FactLesson {
        return FactLesson(
            dateBegin = lesson.dateBegin.time,
            discipline = lesson.discipline,
            type = lesson.lessonType,
            teachers = lesson.teachers,
            classroom = lesson.classroom,
            groups = lesson.groups,
            subgroup = lesson.subgroup
        )
    }

    private fun mapExamDay(
        day: SibgutiExamScheduleDayDto
    ): ExamScheduleDay {
        // Пока зависит от реальной структуры Exam DTO.
        TODO("Implement after defining exam DTO mapping")
    }
}