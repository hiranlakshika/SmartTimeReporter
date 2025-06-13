package com.restable.feature.reportlist.data.remote

import com.restable.core.domain.model.Result
import com.restable.core.domain.model.error.DataError
import com.restable.feature.reportlist.data.remote.dto.ReportDto
import com.restable.network.util.safeSupabaseCall
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.from
import javax.inject.Inject

class SupabaseRemoteReportApi @Inject constructor(
    private val supabaseClient: SupabaseClient
) : RemoteReportApi {
    override suspend fun getTimeReports(): Result<List<ReportDto>, DataError.NetworkError> {
        return safeSupabaseCall {
            supabaseClient.from("reports").select().decodeList<ReportDto>()
        }
    }
}