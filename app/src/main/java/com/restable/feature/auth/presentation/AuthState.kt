package com.restable.feature.auth.presentation

data class AuthState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val isLoggedIn: Boolean = false,
    val email: String = "",
    val password: String = ""
//    val email: String = "hiranlakshika@gmail.com",
//    val password: String = "12345678"
)
