package com.restable.feature.reportlist.data.remote.mappers

import com.restable.feature.reportlist.data.remote.dto.ReportDto
import com.restable.feature.reportlist.domain.model.Report

fun ReportDto.toReport(): Report = Report(
    id = id,
    title = title,
    description = description,
    date = date
)

fun List<ReportDto>.toReports(): List<Report> = map { it.toReport() }