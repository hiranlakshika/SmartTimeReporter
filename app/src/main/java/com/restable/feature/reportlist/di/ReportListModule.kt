package com.restable.feature.reportlist.di

import com.restable.feature.reportlist.data.ReportListRepositoryImpl
import com.restable.feature.reportlist.data.remote.RemoteReportApi
import com.restable.feature.reportlist.data.remote.SupabaseRemoteReportApi
import com.restable.feature.reportlist.domain.repository.ReportListRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.jan.supabase.SupabaseClient

@Module
@InstallIn(SingletonComponent::class)
object ReportListModule {

    @Provides
    fun provideReportListApi(supabaseClient: SupabaseClient): RemoteReportApi {
        return SupabaseRemoteReportApi(
            supabaseClient = supabaseClient
        )
    }

    @Provides
    fun provideReportListRepository(api: RemoteReportApi): ReportListRepository {
        return ReportListRepositoryImpl(api)
    }

}