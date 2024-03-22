package com.ddoczi.tasky.core.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ddoczi.tasky.agenda.domain.model.AgendaItem
import com.ddoczi.tasky.agenda.enums.AgendaType
import com.ddoczi.tasky.agenda.presentation.detail.agenda.AgendaDetailScreen
import com.ddoczi.tasky.agenda.presentation.detail.agenda.AgendaDetailViewModel
import com.ddoczi.tasky.agenda.presentation.home.HomeEvent
import com.ddoczi.tasky.agenda.presentation.home.HomeScreen
import com.ddoczi.tasky.agenda.presentation.home.HomeViewModel
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
    private val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val startDestination = viewModel.state.collectAsStateWithLifecycle().value.isLoggedIn.let { if (it) Route.HOME else Route.LOGIN }
            TaskyTheme {
                val navController = rememberNavController()
                TaskyMainScreen(navController, startDestination, viewModel::onLogout)
            }
        }
    }
}

@Composable
fun TaskyMainScreen(
    navController: NavHostController,
    startDestination: String,
    onLogout: () -> Unit
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
        composable(Route.HOME) {
            val viewModel = hiltViewModel<HomeViewModel>()
            val state by viewModel.state.collectAsStateWithLifecycle()
            HomeScreen(
                state = state,
                onEvent = {event ->
                    when(event) {
                        is HomeEvent.OnLogOutConfirm -> { onLogout() }
                        is HomeEvent.OnRedirectToAgendaItem -> {
                            val agendaType = when(event.agendaItem) {
                                is AgendaItem.Event -> AgendaType.EVENT
                                is AgendaItem.Task -> AgendaType.TASK
                                is AgendaItem.Reminder -> AgendaType.REMINDER
                            }
                            navController.navigate(Route.AGENDA_DETAIL) //+ agendaType
                        }
                        is HomeEvent.OnRedirectToAddAgendaItem -> {
                            navController.navigate(Route.AGENDA_DETAIL) //+ agendaType
                        }
                        else -> { Unit }
                    }
                    viewModel.onEvent(event)
                }
            )
        }
        composable(Route.AGENDA_DETAIL) {backStackEntry ->
            val viewModel = hiltViewModel<AgendaDetailViewModel>()
            val state by viewModel.state.collectAsStateWithLifecycle()
            AgendaDetailScreen(
                state = state,
                onEvent = { event -> viewModel.onEvent(event) }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    TaskyTheme {
        TaskyMainScreen(rememberNavController(), Route.LOGIN, {})
    }
}