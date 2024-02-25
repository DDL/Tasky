package com.ddoczi.tasky

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.ddoczi.tasky.authentication.presentation.registration.RegistrationScreen
import com.ddoczi.tasky.ui.theme.TaskyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TaskyTheme {
                RegistrationScreen()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RegistrationPreview() {
    TaskyTheme {
        RegistrationScreen()
    }
}