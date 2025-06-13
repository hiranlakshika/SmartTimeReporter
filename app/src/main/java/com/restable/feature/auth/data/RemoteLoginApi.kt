package com.restable.feature.auth.data

import com.restable.core.domain.model.Result
import com.restable.core.domain.model.error.DataError

interface RemoteLoginApi {
    suspend fun login(email: String, password: String): Result<Unit, DataError.NetworkError>
    suspend fun logout(): Result<Unit, DataError.NetworkError>
}