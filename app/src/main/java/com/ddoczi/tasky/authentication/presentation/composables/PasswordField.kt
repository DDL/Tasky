package com.ddoczi.tasky.authentication.presentation.composables

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.ddoczi.tasky.authentication.enums.InputFieldType
import com.ddoczi.tasky.core.presentation.composables.TaskyTextField
import com.ddoczi.tasky.ui.theme.TaskyTheme

@Composable
fun PasswordField(
    modifier: Modifier = Modifier,
    onValueChange: (String) -> Unit,
    value: String,
    placeholder: String,
    isValid: Boolean,
    showError: Boolean,
    onPasswordIconClick: () -> Unit,
    isTextVisible: Boolean = true,
){
    TaskyTextField(
        modifier = modifier,
        onValueChange = onValueChange,
        value = value,
        placeholder = placeholder,
        isValid = isValid,
        showError = showError,
        inputFieldType = InputFieldType.PASSWORD,
        onIconClick = onPasswordIconClick,
        isTextVisible = isTextVisible
    )
}

@Preview(showBackground = true)
@Composable
fun PasswordFieldPreview() {
    TaskyTheme {
        PasswordField(
            modifier = Modifier,
            {},
            value = "",
            placeholder = "Password",
            isValid = false,
            showError = true,
            onPasswordIconClick = {},
            isTextVisible = true
        )
    }
}
