package com.ddoczi.tasky.authentication.presentation.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ddoczi.tasky.R
import com.ddoczi.tasky.authentication.presentation.composables.TaskyBackground
import com.ddoczi.tasky.authentication.presentation.composables.TaskyButton
import com.ddoczi.tasky.authentication.presentation.composables.EmailField
import com.ddoczi.tasky.authentication.presentation.composables.PasswordField
import com.ddoczi.tasky.ui.theme.Gray
import com.ddoczi.tasky.ui.theme.TaskyTheme

@Composable
fun LoginScreen(
    state: LoginState,
    onEvent: (LoginEvent) -> Unit,
    onSignUpClick: () -> Unit
) {
    TaskyBackground(
        title = stringResource(R.string.login_title),
        titleWeight = 1.5f,
        contentWeight = 8.5f
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp, top = 50.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            EmailField(
                modifier = Modifier.fillMaxWidth(),
                onValueChange = { onEvent(LoginEvent.OnEmailChanged(it)) },
                value = state.email,
                placeholder = stringResource(id = R.string.placeholder_email_address),
                isValid = state.isEmailValid,
                showError = state.isEmailError
            )
            Spacer(modifier = Modifier.height(16.dp))
            PasswordField(
                modifier = Modifier.fillMaxWidth(),
                onValueChange = { onEvent(LoginEvent.OnPasswordChanged(it)) },
                value = state.password,
                placeholder = stringResource(id = R.string.placeholder_password),
                isValid = state.isPasswordValid,
                showError = state.isPasswordError,
                onPasswordIconClick = { onEvent(LoginEvent.OnPasswordVisibilityToggle) },
                isTextVisible = state.isPasswordVisible
            )
            TaskyButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                onClick = { onEvent(LoginEvent.Login) },
                text = stringResource(id =R.string.btn_label_log_in)
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 60.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.Bottom,
        ) {
            TextButton(
                onClick = onSignUpClick )
            {
                Text(
                    text = stringResource(R.string.link_to_sign_up),
                    color = Gray
                )
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    TaskyTheme {
        LoginScreen(
            state = LoginState(),
            onEvent = { },
            onSignUpClick = { }
        )
    }
}