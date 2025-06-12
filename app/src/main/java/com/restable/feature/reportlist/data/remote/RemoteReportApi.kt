package com.restable.feature.reportlist.data.remote

import com.restable.feature.reportlist.data.remote.dto.ReportDto

interface RemoteReportApi {
    suspend fun getTimeReports(): List<ReportDto>
}