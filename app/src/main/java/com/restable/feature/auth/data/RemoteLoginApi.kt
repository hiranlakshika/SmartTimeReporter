package com.restable.feature.auth.data

interface RemoteLoginApi {
    suspend fun login(email: String, password: String)
    suspend fun logout()
}