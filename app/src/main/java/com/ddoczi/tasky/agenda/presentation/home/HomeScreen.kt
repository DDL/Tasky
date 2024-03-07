package com.ddoczi.tasky.agenda.presentation.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.ddoczi.tasky.authentication.presentation.composables.TaskyBackground
import com.ddoczi.tasky.ui.theme.Gray

@Composable
fun HomeScreen() {
    TaskyBackground(
        title = "",
        titleWeight = 0.2f,
        contentWeight = 9.8f
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Home Screen",
            )
        }
    }
}
