package com.restable.feature.reportlist.data.remote

import com.restable.feature.reportlist.data.remote.dto.ReportDto
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.from
import javax.inject.Inject

class SupabaseRemoteReportApi @Inject constructor(
    private val supabaseClient: SupabaseClient
) : RemoteReportApi {
    override suspend fun getTimeReports(): List<ReportDto> {
        return try {
            supabaseClient.from("reports").select().decodeList<ReportDto>()
        } catch (e: Exception) {
            throw e
        }
    }
}