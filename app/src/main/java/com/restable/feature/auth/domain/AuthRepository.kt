package com.restable.feature.auth.domain

import com.restable.core.domain.model.Result
import com.restable.core.domain.model.error.DataError

interface AuthRepository {
    suspend fun login(email: String, password: String): Result<Unit, DataError>
    suspend fun logout(): Result<Unit, DataError>
}