package com.restable.feature.auth.domain.model

import kotlinx.datetime.Instant

data class UserSession(
    val accessToken: String,
    val refreshToken: String,
    val expiresIn: Long,
    val tokenType: String,
    val user: User? = null,
    val type: String = "",
    val expiresAt: Instant
)