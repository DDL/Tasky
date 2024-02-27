package com.ddoczi.tasky.authentication.presentation.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ddoczi.tasky.R
import com.ddoczi.tasky.ui.theme.TaskyTheme
import com.ddoczi.tasky.ui.theme.Black
import com.ddoczi.tasky.ui.theme.White

@Composable
fun TaskyBackground(
    title: String,
    titleWeight: Float,
    contentWeight: Float,
    content: @Composable () -> Unit
) {
    Surface(
    modifier = Modifier.fillMaxSize(),
    color = Black
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Box(modifier = Modifier
                .fillMaxSize()
                .weight(titleWeight),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = title,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = White)
            }
            Surface(modifier = Modifier
                .fillMaxSize()
                .weight(contentWeight)
                .clip(RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp)),
                color = White
            ) {
                content()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BaseBackgroundPreview() {
    TaskyTheme {
        TaskyBackground(
            title = stringResource(R.string.registration_title),
            titleWeight = 1.5f,
            contentWeight = 8.5f,
            content = {
                Box(modifier = Modifier
                    .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "Test content")
                }
            }
        )
    }
}