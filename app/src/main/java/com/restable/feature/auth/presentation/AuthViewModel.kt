package com.restable.feature.auth.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.restable.core.domain.model.Result
import com.restable.feature.auth.domain.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(private val repository: AuthRepository) : ViewModel() {
    private val _state = MutableStateFlow(AuthState())
    val state: StateFlow<AuthState> get() = _state

    fun onEvent(event: AuthEvent) {
        when (event) {
            is AuthEvent.Login -> {
                login()
            }

            AuthEvent.Logout -> {
                logout()
            }

            is AuthEvent.OnEmailChanged -> {
                _state.update {
                    it.copy(email = event.email)
                }
            }

            is AuthEvent.OnPasswordChanged -> {
                _state.update {
                    it.copy(password = event.password)
                }
            }
        }
    }

    private fun login() = viewModelScope.launch(Dispatchers.IO) {
        when (val result = repository.login(_state.value.email, _state.value.password)) {
            is Result.Error -> {
                _state.update {
                    it.copy(isLoggedIn = false)
                }
            }

            is Result.Success -> {
                _state.update {
                    it.copy(isLoggedIn = true)
                }
            }
        }
    }

    private fun logout() = viewModelScope.launch(Dispatchers.IO) {
        when (val result = repository.logout()) {
            is Result.Error -> {}
            is Result.Success -> {
                _state.update {
                    it.copy(isLoggedIn = false)
                }
            }
        }
    }
}