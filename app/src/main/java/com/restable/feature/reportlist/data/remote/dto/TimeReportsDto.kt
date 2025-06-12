package com.restable.feature.reportlist.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class TimeReportsDto(val reports: List<ReportDto>)
