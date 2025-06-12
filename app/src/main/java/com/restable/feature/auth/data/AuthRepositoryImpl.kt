package com.restable.feature.auth.data

import com.restable.core.domain.model.Result
import com.restable.core.domain.model.error.DataError
import com.restable.feature.auth.domain.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(private val api: RemoteLoginApi) : AuthRepository {
    override suspend fun login(
        email: String,
        password: String
    ): Result<Unit, DataError> {
        return try {
            api.login(email, password)
            Result.Success(Unit)
        } catch (e: Exception) {
            Result.Error(DataError.NetworkError.UNAUTHORIZED)
        }
    }

    override suspend fun logout(): Result<Unit, DataError> {
        return try {
            api.logout()
            Result.Success(Unit)
        } catch (e: Exception) {
            Result.Error(DataError.NetworkError.UNKNOWN)
        }
    }
}