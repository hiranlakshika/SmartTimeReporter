package com.restable.feature.auth.data.mappers

import com.restable.feature.auth.domain.model.UserSession

typealias SupabaseUserSession = io.github.jan.supabase.auth.user.UserSession


fun SupabaseUserSession.toUserSession(): UserSession {
    return UserSession(
        accessToken = accessToken,
        refreshToken = refreshToken,
        expiresIn = expiresIn,
        tokenType = tokenType,
        user = user?.let { com.restable.feature.auth.domain.model.User(it.id, it.email.orEmpty()) },
        type = type,
        expiresAt = expiresAt
    )
}