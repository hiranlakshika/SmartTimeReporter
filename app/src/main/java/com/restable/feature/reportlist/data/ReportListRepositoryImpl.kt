package com.restable.feature.reportlist.data

import com.restable.feature.reportlist.data.remote.RemoteReportApi
import com.restable.feature.reportlist.data.remote.mappers.toReports
import com.restable.feature.reportlist.domain.model.Report
import com.restable.feature.reportlist.domain.repository.ReportListRepository
import javax.inject.Inject

class ReportListRepositoryImpl @Inject constructor(private val api: RemoteReportApi) :
    ReportListRepository {
    override suspend fun getTimeReports(): List<Report> = api.getTimeReports().toReports()
}