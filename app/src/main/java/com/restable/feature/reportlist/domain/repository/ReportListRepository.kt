package com.restable.feature.reportlist.domain.repository

import com.restable.feature.reportlist.domain.model.Report

interface ReportListRepository {
    suspend fun getTimeReports(): List<Report>
}