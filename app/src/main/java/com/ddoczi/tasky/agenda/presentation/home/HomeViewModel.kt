package com.ddoczi.tasky.agenda.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ddoczi.tasky.agenda.domain.model.AgendaItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(

) : ViewModel() {
    private val _state = MutableStateFlow(HomeState())
    val state = _state.asStateFlow()
    fun onEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.OnLogOutClick -> {
                _state.update {
                    it.copy(showLogout = true)
                }
            }
            is HomeEvent.OnLogOutDismiss -> {
                _state.update {
                    it.copy(showLogout = false)
                }
            }
            is HomeEvent.OnDateSelected -> {
                _state.update {
                    it.copy(currentDate = event.date)
                }
            }
            is HomeEvent.OnDaySelected -> {
                _state.update {
                    it.copy(selectedDay = event.day)
                }
            }
            is HomeEvent.OnItemClick -> { Unit }
            is HomeEvent.OnItemOptionsClick -> {
                _state.update {
                    it.copy(
                        selectedAgendaItem = event.agendaItem,
                        showItemOptions = true
                    )
                }
            }
            is HomeEvent.OnItemOptionsDismiss -> {
                _state.update {
                    it.copy(showItemOptions = false)
                }
            }
            is HomeEvent.OnAddAgendaClick -> {
                _state.update {
                    it.copy(showAgendaOptions = true)
                }
            }
            is HomeEvent.OnAgendaItemDismiss -> {
                _state.update {
                    it.copy(showAgendaOptions = false)
                }
            }
            is HomeEvent.OnRefreshAgenda -> { Unit }
            is HomeEvent.OnDeleteItem -> { Unit }
        }
    }
}