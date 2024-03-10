package com.ddoczi.tasky.authentication.presentation.registration

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ddoczi.tasky.R
import com.ddoczi.tasky.authentication.enums.InputFieldType
import com.ddoczi.tasky.core.presentation.composables.TaskyBackground
import com.ddoczi.tasky.core.presentation.composables.TaskyButton
import com.ddoczi.tasky.core.presentation.composables.TaskyTextField
import com.ddoczi.tasky.authentication.presentation.composables.EmailField
import com.ddoczi.tasky.authentication.presentation.composables.PasswordField
import com.ddoczi.tasky.ui.theme.Green
import com.ddoczi.tasky.ui.theme.TaskyTheme
@Composable
fun RegistrationScreen(
    state: RegistrationState,
    onEvent: (RegistrationEvent) -> Unit
) {
    TaskyBackground(
        title = stringResource(R.string.registration_title),
        titleWeight = 1.5f,
        contentWeight = 8.5f
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp, top = 50.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TaskyTextField(
                modifier = Modifier.fillMaxWidth(),
                onValueChange = { onEvent(RegistrationEvent.OnFullNameChanged(it)) },
                value = state.fullName,
                placeholder = stringResource(R.string.placeholder_name),
                keyboardType = KeyboardType.Text,
                isValid = state.isFullNameValid,
                showError = state.fullNameError,
                icon = Icons.Default.Check,
                iconColor = Green,
                inputFieldType = InputFieldType.FULL_NAME
            )
            Spacer(modifier = Modifier.height(16.dp))
            EmailField(
                modifier = Modifier.fillMaxWidth(),
                onValueChange = { onEvent(RegistrationEvent.OnEmailChanged(it)) },
                value = state.email,
                placeholder = stringResource(R.string.placeholder_email_address),
                isValid = state.isEmailValid,
                showError = state.emailError
            )
            Spacer(modifier = Modifier.height(16.dp))
            PasswordField(
                modifier = Modifier.fillMaxWidth(),
                onValueChange = { onEvent(RegistrationEvent.OnPasswordChanged(it)) },
                value = state.password,
                placeholder = stringResource(R.string.placeholder_password),
                isValid = state.isPasswordValid,
                showError = state.passwordError,
                onPasswordIconClick = { onEvent(RegistrationEvent.OnPasswordVisibilityToggle) },
                isTextVisible = state.isPasswordVisible
            )
            Spacer(modifier = Modifier.height(16.dp))
            TaskyButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp, bottom = 16.dp),
                onClick = { onEvent(RegistrationEvent.Submit) },
                text = stringResource(R.string.btn_label_get_started)
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, bottom = 60.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.Bottom
        ) {
            TaskyButton(
                onClick = { onEvent(RegistrationEvent.OnNavigateBack) },
                shape = RoundedCornerShape(10.dp),
                icon = Icons.Default.ArrowBackIosNew,
                contentDescription = stringResource(R.string.content_desc_back_to_login_button)
                )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RegistrationScreenPreview() {
    TaskyTheme {
        RegistrationScreen(
            state = RegistrationState(),
            onEvent = { }
        )
    }
}