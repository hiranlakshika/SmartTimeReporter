package com.restable.feature.auth.data

import com.restable.core.domain.model.Result
import com.restable.core.domain.model.error.DataError
import io.github.jan.supabase.auth.user.UserSession

interface RemoteLoginApi {
    suspend fun login(email: String, password: String): Result<UserSession?, DataError.NetworkError>
    suspend fun logout(): Result<Unit, DataError.NetworkError>
}