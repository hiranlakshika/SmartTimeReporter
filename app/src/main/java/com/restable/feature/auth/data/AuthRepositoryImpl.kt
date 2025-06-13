package com.restable.feature.auth.data

import com.restable.core.domain.model.Result
import com.restable.core.domain.model.error.DataError
import com.restable.feature.auth.data.mappers.toUserSession
import com.restable.feature.auth.domain.AuthRepository
import com.restable.feature.auth.domain.model.UserSession
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(private val api: RemoteLoginApi) : AuthRepository {
    override suspend fun login(
        email: String,
        password: String
    ): Result<UserSession, DataError.NetworkError> {
        return when (val result = api.login(email, password)) {
            is Result.Success -> if (result.data != null) Result.Success(result.data.toUserSession()) else Result.Error(
                DataError.NetworkError.UNAUTHORIZED
            )

            is Result.Error -> Result.Error(result.error)
        }
    }

    override suspend fun logout(): Result<Unit, DataError.NetworkError> {
        return api.logout()
    }
}