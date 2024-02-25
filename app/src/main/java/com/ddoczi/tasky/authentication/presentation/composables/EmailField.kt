package com.ddoczi.tasky.authentication.presentation.composables

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.ddoczi.tasky.authentication.enums.InputFieldType
import com.ddoczi.tasky.ui.theme.TaskyTheme

@Composable
fun EmailField(
    modifier: Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    isValid: Boolean,
    showError: Boolean
) {
    BaseTextField(
        modifier = modifier,
        onValueChange = onValueChange,
        value = value,
        placeholder = placeholder,
        isValid = isValid,
        showError = showError,
        inputFieldType = InputFieldType.EMAIL
    )
}

@Preview(showBackground = true)
@Composable
fun EmailFieldPreview() {
    TaskyTheme {
        EmailField(
            modifier = Modifier,
            onValueChange = { },
            value = "",
            placeholder = "Email address",
            isValid = false,
            showError = true
        )
    }
}