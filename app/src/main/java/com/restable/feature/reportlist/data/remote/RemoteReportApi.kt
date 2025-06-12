package com.restable.feature.reportlist.data.remote

import com.restable.feature.reportlist.data.remote.dto.ReportDto
import retrofit2.http.GET

interface RemoteReportApi {
    @GET("/api/time-reports")
    suspend fun getTimeReports(): List<ReportDto>
}