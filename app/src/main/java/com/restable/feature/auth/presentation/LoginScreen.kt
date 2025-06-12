package com.restable.feature.auth.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    viewModel: AuthViewModel = hiltViewModel(),
    onLoginSuccess: () -> Unit
) {

    val state = viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(state.value.isLoggedIn) {
        if (state.value.isLoggedIn) {
            onLoginSuccess()
        }
    }

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Login")
        Spacer(modifier = Modifier.height(12.dp))
        TextField(
            value = state.value.email,
            onValueChange = {
                viewModel.onEvent(AuthEvent.OnEmailChanged(it.trim()))
            },
            label = { Text(text = "Email") }
        )
        TextField(
            value = state.value.password,
            onValueChange = {
                viewModel.onEvent(AuthEvent.OnPasswordChanged(it.trim()))
            },
            label = { Text(text = "Password") }
        )
//        PasswordTextField(onValueChange = {
//            viewModel.onEvent(AuthEvent.OnPasswordChanged(it))
//        })
        ElevatedButton(onClick = { viewModel.onEvent(AuthEvent.Login) }) {
            Text("Login")
        }
    }
}