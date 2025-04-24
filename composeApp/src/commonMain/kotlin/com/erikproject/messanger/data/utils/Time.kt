package com.erikproject.messanger.data.utils

import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.format
import kotlinx.datetime.format.DateTimeFormat
import kotlinx.datetime.toLocalDateTime

object Time {
    fun getFormattedDateTime(): String {
        val now = Clock.System.now()
        val localDateTime = now.toLocalDateTime(TimeZone.currentSystemDefault())

        return "${localDateTime.year}-" +
                "${localDateTime.monthNumber.toString().padStart(2, '0')}-" +
                "${localDateTime.dayOfMonth.toString().padStart(2, '0')} " +
                "${localDateTime.hour.toString().padStart(2, '0')}:" +
                "${localDateTime.minute.toString().padStart(2, '0')}:" +
                "${localDateTime.second.toString().padStart(2, '0')}"
    }
    fun getTimeMills(): Long {
        return Clock.System.now().toEpochMilliseconds()
    }

    fun getSQLDateTime(noTime: Boolean): String {
        val now = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())

        val date = "${now.year}-${now.monthNumber}-${now.dayOfMonth} " +
                "${now.hour}:${now.minute}:${now.second}"

        return if (noTime) date.substring(0, 10) else date
    }

    fun compareDates(dateStr1: String, dateStr2: String): Int {
    // Парсим обе даты
    val (year1, month1, day1, hour1, minute1, second1) = parseDateTime(dateStr1)
    val (year2, month2, day2, hour2, minute2, second2) = parseDateTime(dateStr2)

    // Сравниваем по компонентам от старших к младшим
    return compareValues(year1, year2).takeIf { it != 0 }
        ?: compareValues(month1, month2).takeIf { it != 0 }
        ?: compareValues(day1, day2).takeIf { it != 0 }
        ?: compareValues(hour1, hour2).takeIf { it != 0 }
        ?: compareValues(minute1, minute2).takeIf { it != 0 }
        ?: compareValues(second1, second2)
}

private fun parseDateTime(dateTimeStr: String): DateTimeComponents {
    val (datePart, timePart) = dateTimeStr.split(' ')
    val (year, month, day) = datePart.split('-').map { it.toInt() }
    val (hour, minute, second) = timePart.split(':').map { it.toInt() }

    return DateTimeComponents(year, month, day, hour, minute, second)
}

private data class DateTimeComponents(
    val year: Int,
    val month: Int,
    val day: Int,
    val hour: Int,
    val minute: Int,
    val second: Int
)

}