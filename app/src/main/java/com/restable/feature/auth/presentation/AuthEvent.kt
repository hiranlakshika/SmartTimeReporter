package com.restable.feature.auth.presentation

sealed interface AuthEvent {
    data object Login : AuthEvent
    object Logout : AuthEvent
    data class OnEmailChanged(val email: String) : AuthEvent
    data class OnPasswordChanged(val password: String) : AuthEvent
}