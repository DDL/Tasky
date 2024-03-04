package com.ddoczi.tasky

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ddoczi.tasky.authentication.presentation.login.LoginEvent
import com.ddoczi.tasky.authentication.presentation.login.LoginScreen
import com.ddoczi.tasky.authentication.presentation.login.LoginViewModel
import com.ddoczi.tasky.authentication.presentation.registration.RegistrationEvent
import com.ddoczi.tasky.authentication.presentation.registration.RegistrationScreen
import com.ddoczi.tasky.authentication.presentation.registration.RegistrationViewModel
import com.ddoczi.tasky.core.navigation.Route
import com.ddoczi.tasky.ui.theme.TaskyTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TaskyTheme {
                val navController = rememberNavController()
                TaskyMainScreen(navController, Route.LOGIN)
            }
        }
    }
}

@Composable
fun TaskyMainScreen(
    navController: NavHostController,
    startDestination: String
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(Route.LOGIN) {
            val viewModel = hiltViewModel<LoginViewModel>()
            val state by viewModel.state.collectAsStateWithLifecycle()
            LoginScreen(
                state = state,
                onEvent = { event ->
                    when(event) {
                        is LoginEvent.OnSignUpClick -> { navController.navigate(Route.REGISTRATION) }
                        else -> { Unit }
                    }
                    viewModel.onEvent(event)
                }
            )
        }
        composable(Route.REGISTRATION) {
            val viewModel = hiltViewModel<RegistrationViewModel>()
            val state by viewModel.state.collectAsStateWithLifecycle()
            RegistrationScreen(
                state = state,
                onEvent = { event ->
                    when(event) {
                        is RegistrationEvent.OnNavigateBack -> { navController.navigateUp() }
                        else -> { Unit }
                    }
                    viewModel.onEvent(event)
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    TaskyTheme {
        TaskyMainScreen( rememberNavController(), Route.LOGIN)
    }
}