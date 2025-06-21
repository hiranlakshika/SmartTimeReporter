package com.restable.feature.auth.data

import com.restable.core.domain.model.Result
import com.restable.core.domain.model.error.DataError
import io.github.jan.supabase.auth.user.UserInfo
import kotlinx.coroutines.test.runTest
import kotlinx.datetime.Instant
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.whenever
import io.github.jan.supabase.auth.user.UserSession as SupabaseUserSession

class AuthRepositoryImplTest {

    @Mock
    private lateinit var mockApi: RemoteLoginApi

    private lateinit var repository: AuthRepositoryImpl

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        repository = AuthRepositoryImpl(mockApi)
    }

    @Test
    fun `login success with valid user session returns mapped user session`() = runTest {
        // Given
        val email = "test@example.com"
        val password = "password123"
        val mockUserInfo = UserInfo(
            id = "user123",
            aud = "authenticated",
            email = email,
            emailConfirmedAt = null,
            phone = null,
            phoneConfirmedAt = null,
            createdAt = Instant.fromEpochSeconds(1000),
            lastSignInAt = null,
            role = null,
            updatedAt = null,
        )
        val mockSupabaseSession = SupabaseUserSession(
            accessToken = "access_token",
            refreshToken = "refresh_token",
            expiresIn = 3600L,
            tokenType = "Bearer",
            user = mockUserInfo,
            type = "password",
            expiresAt = Instant.fromEpochSeconds(4600)
        )
        val apiResult: Result<SupabaseUserSession?, DataError.NetworkError> =
            Result.Success(mockSupabaseSession)

        whenever(mockApi.login(email, password)).thenReturn(apiResult)

        // When
        val result = repository.login(email, password)

        // Then
        assertTrue(result is Result.Success)
        val userSession = (result as Result.Success).data
        assertEquals("access_token", userSession.accessToken)
        assertEquals("refresh_token", userSession.refreshToken)
        assertEquals(3600L, userSession.expiresIn)
        assertEquals("Bearer", userSession.tokenType)
        assertEquals("password", userSession.type)
        assertEquals(Instant.fromEpochSeconds(4600), userSession.expiresAt)
        assertNotNull(userSession.user)
        assertEquals("user123", userSession.user?.id)
        assertEquals(email, userSession.user?.email)
    }

    @Test
    fun `login success with null user session returns unauthorized error`() = runTest {
        // Given
        val email = "test@example.com"
        val password = "password123"
        val apiResult: Result<SupabaseUserSession?, DataError.NetworkError> = Result.Success(null)

        whenever(mockApi.login(email, password)).thenReturn(apiResult)

        // When
        val result = repository.login(email, password)

        // Then
        assertTrue(result is Result.Error)
        val error = (result as Result.Error).error
        assertEquals(DataError.NetworkError.UNAUTHORIZED, error)
    }

    @Test
    fun `login failure returns api error`() = runTest {
        // Given
        val email = "test@example.com"
        val password = "wrongpassword"
        val networkError = DataError.NetworkError.SERVER_ERROR
        val apiResult: Result<SupabaseUserSession?, DataError.NetworkError> =
            Result.Error(networkError)

        whenever(mockApi.login(email, password)).thenReturn(apiResult)

        // When
        val result = repository.login(email, password)

        // Then
        assertTrue(result is Result.Error)
        val error = (result as Result.Error).error
        assertEquals(networkError, error)
    }

    @Test
    fun `login with network timeout returns timeout error`() = runTest {
        // Given
        val email = "test@example.com"
        val password = "password123"
        val timeoutError = DataError.NetworkError.REQUEST_TIMEOUT
        val apiResult: Result<SupabaseUserSession?, DataError.NetworkError> =
            Result.Error(timeoutError)

        whenever(mockApi.login(email, password)).thenReturn(apiResult)

        // When
        val result = repository.login(email, password)

        // Then
        assertTrue(result is Result.Error)
        assertEquals(timeoutError, (result as Result.Error).error)
    }

    @Test
    fun `login with no internet returns no internet error`() = runTest {
        // Given
        val email = "test@example.com"
        val password = "password123"
        val noInternetError = DataError.NetworkError.NO_INTERNET
        val apiResult: Result<SupabaseUserSession?, DataError.NetworkError> =
            Result.Error(noInternetError)

        whenever(mockApi.login(email, password)).thenReturn(apiResult)

        // When
        val result = repository.login(email, password)

        // Then
        assertTrue(result is Result.Error)
        assertEquals(noInternetError, (result as Result.Error).error)
    }

    @Test
    fun `logout success returns unit result`() = runTest {
        // Given
        val apiResult: Result<Unit, DataError.NetworkError> = Result.Success(Unit)
        whenever(mockApi.logout()).thenReturn(apiResult)

        // When
        val result = repository.logout()

        // Then
        assertTrue(result is Result.Success)
        assertEquals(Unit, (result as Result.Success).data)
    }

    @Test
    fun `logout failure returns api error`() = runTest {
        // Given
        val networkError = DataError.NetworkError.SERVER_ERROR
        val apiResult: Result<Unit, DataError.NetworkError> = Result.Error(networkError)
        whenever(mockApi.logout()).thenReturn(apiResult)

        // When
        val result = repository.logout()

        // Then
        assertTrue(result is Result.Error)
        assertEquals(networkError, (result as Result.Error).error)
    }

    @Test
    fun `logout with unauthorized error returns unauthorized`() = runTest {
        // Given
        val unauthorizedError = DataError.NetworkError.UNAUTHORIZED
        val apiResult: Result<Unit, DataError.NetworkError> = Result.Error(unauthorizedError)
        whenever(mockApi.logout()).thenReturn(apiResult)

        // When
        val result = repository.logout()

        // Then
        assertTrue(result is Result.Error)
        assertEquals(unauthorizedError, (result as Result.Error).error)
    }
}