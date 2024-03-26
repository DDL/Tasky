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
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.ddoczi.tasky.agenda.domain.model.AgendaItem
import com.ddoczi.tasky.agenda.enums.AgendaOption
import com.ddoczi.tasky.agenda.enums.AgendaType
import com.ddoczi.tasky.agenda.presentation.detail.agenda.AgendaDetailEvent
import com.ddoczi.tasky.agenda.presentation.detail.agenda.AgendaDetailScreen
import com.ddoczi.tasky.agenda.presentation.detail.agenda.AgendaDetailViewModel
import com.ddoczi.tasky.agenda.presentation.editor.AgendaEditorEvent
import com.ddoczi.tasky.agenda.presentation.editor.AgendaEditorScreen
import com.ddoczi.tasky.agenda.presentation.editor.AgendaEditorViewModel
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
                            if(event.option == AgendaOption.DELETE) {
                                //Ask if user is sure to delete
                                viewModel.onEvent(HomeEvent.OnDeleteItem(event.agendaItem))
                            } else {
                                navController.navigate("${Route.AGENDA_DETAIL}/${agendaType}?option=${event.option}?id=${event.agendaItem.agendaItemId}")
                            }
                        }
                        is HomeEvent.OnRedirectToAddAgendaItem -> {
                            navController.navigate("${Route.AGENDA_DETAIL}/${event.agendaType}")
                        }
                        else -> { Unit }
                    }
                    viewModel.onEvent(event)
                }
            )
        }
        composable(
            route = Route.AGENDA_DETAIL + "/{agendaType}?option={option}?id={id}",
            arguments = listOf(
                navArgument("agendaType") {
                    type = NavType.EnumType(AgendaType::class.java)
                    nullable = false
                },
                navArgument("option") {
                    type = NavType.StringType
                    nullable = true
                },
                navArgument("id") {
                    type = NavType.StringType
                    nullable = true
                }
            )
        ) { backStackEntry ->
            val agendaType = backStackEntry.arguments?.get("agendaType") as AgendaType
            val option = backStackEntry.arguments?.getString("option")
            val viewModel = hiltViewModel<AgendaDetailViewModel>()
            val state by viewModel.state.collectAsStateWithLifecycle()
            AgendaDetailScreen(
                state = state,
                onEvent = { event ->
                    when(event) {
                        is AgendaDetailEvent.OnClose -> { navController.navigateUp() }
                        is AgendaDetailEvent.OnOpenEditor -> {
                            navController.navigate("${Route.AGENDA_EDITOR}?id=${event.id}&title=${event.title}&body=${event.body}")
                        }
                        else -> { Unit }
                    }
                    viewModel.onEvent(event)
                },
//                agendaType = agendaType,
//                agendaOption = option?.let { AgendaOption.valueOf(it) }
            )
        }
        composable(
            route = Route.AGENDA_EDITOR + "?id={id}&title={title}&body={body}",
            arguments = listOf(
                navArgument("id") {
                    type = NavType.StringType
                    nullable = true
                },
                navArgument("title") {
                    type = NavType.StringType
                    nullable = true
                },
                navArgument("body") {
                    type = NavType.StringType
                    nullable = true
                }
            )
        ) { backStackEntry ->
//            All these params can be retrieved from SavedStateHandle in your VM
            val id = backStackEntry.arguments?.getString("id")
            val title = backStackEntry.arguments?.getString("title")
            val body = backStackEntry.arguments?.getString("body")
            val viewModel = hiltViewModel<AgendaEditorViewModel>()
            val state by viewModel.state.collectAsStateWithLifecycle()
            AgendaEditorScreen(
                state = state,
                onEvent = { event ->
                    when(event) {
                        is AgendaEditorEvent.OnBack -> { navController.navigateUp() }
                        else -> { Unit }
                    }
                    viewModel.onEvent(event)
                },
                title = title ?: "",
                body = body ?: ""
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