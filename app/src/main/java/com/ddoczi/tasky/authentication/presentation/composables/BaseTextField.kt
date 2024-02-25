package com.ddoczi.tasky.authentication.presentation.composables

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import com.ddoczi.tasky.authentication.enums.InputFieldType
import com.ddoczi.tasky.ui.theme.TaskyTheme

@Composable
fun BaseTextField(
    modifier: Modifier = Modifier,
    onValueChange: (String) -> Unit,
    value: String,
    singleLine: Boolean = true,
    maxLines: Int = 1,
    placeholder: String = "",
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Done,
    isValid: Boolean = false,
    showError: Boolean = false,
    icon: ImageVector? = null,
    iconColor: Color? = null,
    contentDescription: String? = null,
    inputFieldType: InputFieldType? = null,
    onIconClick: () -> Unit = {},
    isTextVisible: Boolean = true
) {
    OutlinedTextField(
        modifier = modifier,
        value = value,
        onValueChange = onValueChange,
        singleLine = singleLine,
        maxLines = maxLines,
        placeholder = { Text(text = placeholder) },
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType,
            imeAction = imeAction
        ),
        trailingIcon = {
            BaseIcon(
                icon = icon,
                iconColor = iconColor,
                contentDescription = contentDescription,
                isValid = isValid,
                inputFieldType = inputFieldType,
                hasError = if(inputFieldType == InputFieldType.PASSWORD) showError else false,
                onIconClick = onIconClick
            )},
        isError = showError,
        visualTransformation = if (isTextVisible) VisualTransformation.None else PasswordVisualTransformation()
    )
}