package com.restable.feature.reportlist.data

import com.restable.feature.reportlist.data.remote.RemoteReportApi
import com.restable.feature.reportlist.domain.model.Report
import com.restable.feature.reportlist.domain.repository.ReportListRepository

class ReportListRepositoryImpl(private val api: RemoteReportApi) : ReportListRepository {
    override suspend fun getTimeReports(): List<Report> {
        TODO("Not yet implemented")
    }
}