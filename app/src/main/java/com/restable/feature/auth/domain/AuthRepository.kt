package com.restable.feature.auth.domain

import com.restable.core.domain.model.Result
import com.restable.core.domain.model.error.DataError
import com.restable.feature.auth.domain.model.UserSession

interface AuthRepository {
    suspend fun login(email: String, password: String): Result<UserSession, DataError.NetworkError>
    suspend fun logout(): Result<Unit, DataError.NetworkError>
}