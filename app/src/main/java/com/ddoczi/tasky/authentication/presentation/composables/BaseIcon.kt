package com.ddoczi.tasky.authentication.presentation.composables

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import com.ddoczi.tasky.R
import com.ddoczi.tasky.authentication.enums.InputFieldType
import com.ddoczi.tasky.ui.theme.Gray
import com.ddoczi.tasky.ui.theme.Green
import com.ddoczi.tasky.ui.theme.Red

@Composable
fun TaskyTextFieldIcon(
    icon: ImageVector?,
    iconColor: Color?,
    contentDescription: String?,
    isValid: Boolean,
    showText: Boolean = false,
    inputFieldType: InputFieldType? = null,
    hasError: Boolean = false,
    onIconClick: () -> Unit = {}
) {
    if (inputFieldType == null) return
    when (inputFieldType) {
        InputFieldType.FULL_NAME -> {
            if (isValid) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = stringResource(R.string.content_desc_full_name_valid_icon),
                    tint = Green
                )
            }
        }
        InputFieldType.EMAIL -> {
            if (isValid) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = stringResource(R.string.content_desc_email_valid_icon),
                    tint = Green
                )
            }
        }
        InputFieldType.PASSWORD -> {
            IconButton(onClick = onIconClick) {
                val passwordIcon = if (showText) Icons.Default.Visibility else Icons.Default.VisibilityOff
                Icon(
                    imageVector = passwordIcon,
                    contentDescription = stringResource(R.string.content_desc_show_hide_password_icon),
                    tint = if(hasError) Red else Gray
                )
            }
        }
        else -> {
            if(icon != null && iconColor != null && contentDescription != null) {
                Icon(
                    imageVector = icon,
                    contentDescription = contentDescription,
                    tint = iconColor
                )
            }
        }
    }
}