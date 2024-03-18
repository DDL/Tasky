package com.ddoczi.tasky.core.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ddoczi.tasky.agenda.data.local.AgendaDatabase
import com.ddoczi.tasky.core.domain.preferences.Preferences
import com.ddoczi.tasky.core.domain.reposotory.AuthenticationRepository
import com.ddoczi.tasky.util.DispatcherProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val authenticationRepository: AuthenticationRepository,
) : ViewModel() {
    private val _state = MutableStateFlow(MainState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            authenticationRepository.authenticate()
                .onSuccess {
                    println(it)
                    _state.update {
                        it.copy(
                            isLoggedIn = true,
                            isLoading = false
                        )
                    }
                }
                .onFailure {
                    println(it)
                    _state.update {
                        it.copy(
                            isLoggedIn = false,
                            isLoading = false
                        )
                    }
                }
       }
    }
    fun onLogout() {
        viewModelScope.launch {
            println("logout")
        }
    }
}