package com.restable.feature.reportlist.data.remote.mappers

import com.restable.feature.reportlist.data.remote.dto.ReportDto
import com.restable.feature.reportlist.domain.model.Report
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

fun ReportDto.toReport(): Report = Report(
    id = id,
    title = title,
    description = description,
    createdAt = formatInstantToString(createdAt),
    duration = duration
)

fun List<ReportDto>.toReports(): List<Report> = map { it.toReport() }

private fun formatInstantToString(instant: Instant): String {
    val localDateTime = instant.toLocalDateTime(TimeZone.currentSystemDefault())
    return "${localDateTime.date} ${localDateTime.hour}:${localDateTime.minute}"
}