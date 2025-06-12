package com.restable.feature.reportlist.domain.repository

import com.restable.core.domain.model.Result
import com.restable.core.domain.model.error.DataError
import com.restable.feature.reportlist.domain.model.Report

interface ReportListRepository {
    suspend fun getTimeReports(): Result<List<Report>, DataError.NetworkError>
}