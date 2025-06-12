package com.restable.feature.reportlist.di

import com.restable.feature.reportlist.data.ReportListRepositoryImpl
import com.restable.feature.reportlist.data.remote.RemoteReportApi
import com.restable.feature.reportlist.domain.repository.ReportListRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object ReportListModule {

    @Provides
    fun provideRemoteReportApi(retrofit: Retrofit): RemoteReportApi {
        return retrofit.create(RemoteReportApi::class.java)
    }

    @Provides
    fun provideReportListRepository(api: RemoteReportApi): ReportListRepository {
        return ReportListRepositoryImpl(api)
    }

}