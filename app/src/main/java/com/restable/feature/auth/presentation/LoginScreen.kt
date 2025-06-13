package com.restable.feature.auth.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalAutofillManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.restable.R
import com.restable.feature.auth.presentation.components.PasswordTextField
import com.restable.feature.auth.presentation.components.EmailTextField

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    viewModel: AuthViewModel = hiltViewModel(),
    onLoginSuccess: () -> Unit
) {

    val state = viewModel.state.collectAsStateWithLifecycle()

    val autofillManager = LocalAutofillManager.current

    LaunchedEffect(state.value.isLoggedIn) {
        if (state.value.isLoggedIn) {
            onLoginSuccess()
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 12.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(R.string.sign_in),
            style = MaterialTheme.typography.headlineLarge
        )
        Spacer(modifier = Modifier.height(12.dp))
        EmailTextField(
            value = state.value.email,
            onValueChange = {
                viewModel.onEvent(AuthEvent.OnEmailChanged(it.trim()))
            },
            label = "Email Address"
        )
        PasswordTextField(onValueChange = {
            viewModel.onEvent(AuthEvent.OnPasswordChanged(it))
        })
        Spacer(modifier = Modifier.height(12.dp))
        ElevatedButton(onClick = {
            autofillManager?.commit()
            viewModel.onEvent(AuthEvent.Login)
        }) {
            Text(stringResource(R.string.login))
        }
    }
}