package com.restable.feature.auth.presentation

sealed interface AuthEvent {
    data class Login(val email: String, val password: String) : AuthEvent
    object Logout : AuthEvent
}