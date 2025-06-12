package com.restable.feature.reportlist.data

import com.restable.core.domain.model.Result
import com.restable.core.domain.model.error.DataError
import com.restable.feature.reportlist.data.remote.RemoteReportApi
import com.restable.feature.reportlist.data.remote.mappers.toReports
import com.restable.feature.reportlist.domain.model.Report
import com.restable.feature.reportlist.domain.repository.ReportListRepository
import javax.inject.Inject

class ReportListRepositoryImpl @Inject constructor(private val api: RemoteReportApi) :
    ReportListRepository {
    override suspend fun getTimeReports(): Result<List<Report>, DataError.NetworkError> {
        return try {
            Result.Success(api.getTimeReports().toReports())
        } catch (e: Exception) {
            Result.Error(DataError.NetworkError.SERVER_ERROR)
        }
    }
}