package com.restable.feature.auth.di

import com.restable.feature.auth.data.AuthRepositoryImpl
import com.restable.feature.auth.data.RemoteLoginApi
import com.restable.feature.auth.data.SupabaseRemoteLoginApi
import com.restable.feature.auth.domain.AuthRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.jan.supabase.SupabaseClient

@Module
@InstallIn(SingletonComponent::class)
object AuthModule {

    @Provides
    fun provideAuthApi(supabaseClient: SupabaseClient): RemoteLoginApi {
        return SupabaseRemoteLoginApi(supabaseClient)
    }

    @Provides
    fun provideAuthRepository(api: RemoteLoginApi): AuthRepository {
        return AuthRepositoryImpl(api)
    }
}