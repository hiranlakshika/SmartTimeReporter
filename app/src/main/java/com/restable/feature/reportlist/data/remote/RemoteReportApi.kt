package com.restable.feature.reportlist.data.remote

import com.restable.core.domain.model.Result
import com.restable.core.domain.model.error.DataError
import com.restable.feature.reportlist.data.remote.dto.ReportDto

interface RemoteReportApi {
    suspend fun getTimeReports(): Result<List<ReportDto>, DataError.NetworkError>
}