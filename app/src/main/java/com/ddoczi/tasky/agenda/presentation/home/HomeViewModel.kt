package com.ddoczi.tasky.agenda.presentation.home

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(

) : ViewModel() {
    private val _state = MutableStateFlow(HomeState())
    val state = _state.asStateFlow()

    fun onEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.OnLogOut -> {
                _state.update {
                    it.copy(showLogout = true)
                }
            }
            is HomeEvent.OnLogOutDismiss -> {
                _state.update {
                    it.copy(showLogout = false)
                }
            }
        }
    }
}