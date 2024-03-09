package com.ddoczi.tasky.core.presentation.composables

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ddoczi.tasky.ui.theme.TaskyTheme

@Composable
fun TaskyButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    shape: RoundedCornerShape? = null,
    icon: ImageVector? = null,
    text: String? = null,
    contentDescription: String? = null
) {
    Button(
        modifier = modifier,
        onClick = onClick,
        shape = shape?: RoundedCornerShape(10.dp),
    ) {
        if (icon != null) {
            Icon(
                imageVector = icon,
                contentDescription = contentDescription
            )
        } else {
            Text(
                text = text?: "",
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BaseButtonPreview() {
    TaskyTheme {
        TaskyButton(
            modifier = Modifier,
            onClick = { },
            shape = RoundedCornerShape(10.dp),
            icon = Icons.Default.ArrowBackIosNew,
            //text = "Lorem Ipsum"
        )
    }
}