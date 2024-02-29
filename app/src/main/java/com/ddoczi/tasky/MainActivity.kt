package com.ddoczi.tasky

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.tooling.preview.Preview
import com.ddoczi.tasky.authentication.presentation.login.LoginScreen
import com.ddoczi.tasky.authentication.presentation.login.LoginViewModel
import com.ddoczi.tasky.ui.theme.TaskyTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val loginViewModel by viewModels<LoginViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TaskyTheme {
                LoginScreen(
                    loginViewModel.state.collectAsState().value,
                    loginViewModel::onEvent
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RegistrationPreview() {
    TaskyTheme {
//        RegistrationScreen()
    }
}