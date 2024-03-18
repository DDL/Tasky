package com.ddoczi.tasky.agenda.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ddoczi.tasky.agenda.domain.model.AgendaItem
import com.ddoczi.tasky.agenda.domain.repository.EventRepository
import com.ddoczi.tasky.agenda.domain.repository.ReminderRepository
import com.ddoczi.tasky.agenda.domain.repository.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val eventRepository: EventRepository,
    private val taskRepository: TaskRepository,
    private val reminderRepository: ReminderRepository
) : ViewModel() {
    private val _state = MutableStateFlow(HomeState())
    val state = _state.asStateFlow()
    fun onEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.OnLogOutClick -> {
                _state.update { it.copy(showLogout = true) }
            }
            is HomeEvent.OnLogOutDismiss -> {
                _state.update { it.copy(showLogout = false) }
            }
            is HomeEvent.OnDateSelected -> {
                _state.update {
                    it.copy(
                        currentDate = event.date,
                        selectedDay = 0
                    )
                }
            }
            is HomeEvent.OnDaySelected -> {
                _state.update { it.copy(selectedDay = event.day) }
            }
            is HomeEvent.OnItemClick -> {
                viewModelScope.launch {
                    if(event.agendaItem is AgendaItem.Task){
                        taskRepository.changeTaskStatus(event.agendaItem.taskId, !event.agendaItem.isDone)
                    }
                }
            }
            is HomeEvent.OnItemOptionsClick -> {
                _state.update {
                    it.copy(
                        selectedAgendaItem = event.agendaItem,
                        showItemOptions = true
                    )
                }
            }
            is HomeEvent.OnItemOptionsDismiss -> {
                _state.update { it.copy(showItemOptions = false) }
            }
            is HomeEvent.OnAddAgendaClick -> {
                _state.update { it.copy(showAgendaOptions = true) }
            }
            is HomeEvent.OnAgendaItemDismiss -> {
                _state.update { it.copy(showAgendaOptions = false) }
            }
            is HomeEvent.OnRefreshAgenda -> { Unit }
            is HomeEvent.OnDeleteItem -> {
                viewModelScope.launch {
                    when (event.agendaItem) {
                        is AgendaItem.Event -> { eventRepository.deleteEventById(event.agendaItem.eventId) }
                        is AgendaItem.Task -> { taskRepository.deleteTaskById(event.agendaItem.taskId) }
                        is AgendaItem.Reminder -> { reminderRepository.deleteReminderById(event.agendaItem.reminderId) }
                    }
                }
            }
            else -> { Unit }
        }
    }
}