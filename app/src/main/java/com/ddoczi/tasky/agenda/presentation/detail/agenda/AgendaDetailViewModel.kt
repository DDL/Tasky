package com.ddoczi.tasky.agenda.presentation.detail.agenda

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ddoczi.tasky.agenda.domain.model.AgendaItem
import com.ddoczi.tasky.agenda.domain.repository.EventRepository
import com.ddoczi.tasky.agenda.domain.repository.ReminderRepository
import com.ddoczi.tasky.agenda.domain.repository.TaskRepository
import com.ddoczi.tasky.agenda.enums.AgendaType
import com.ddoczi.tasky.core.util.toLocalDateTime
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.NonCancellable
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class AgendaDetailViewModel @Inject constructor(
    private val eventRepository: EventRepository,
    private val taskRepository: TaskRepository,
    private val reminderRepository: ReminderRepository
): ViewModel() {
    private val _state = MutableStateFlow(AgendaDetailState())
    val state = _state.asStateFlow()

    fun onEvent(event: AgendaDetailEvent) {
        when(event) {
            is AgendaDetailEvent.OnEdit -> {
                _state.update { it.copy(isEditing = true) }
            }
            is AgendaDetailEvent.OnSave -> {
                _state.update { it.copy(isEditing = false) }
                viewModelScope.launch {
                    when (state.value.agendaType) {
                        AgendaType.TASK -> taskRepository.insertTask(
                            AgendaItem.Task(
                                taskId = state.value.id.ifEmpty { UUID.randomUUID().toString() },
                                taskTitle = state.value.title,
                                taskDescription = state.value.description,
                                taskDate = state.value.fromDate.toEpochDay().toLocalDateTime(),
                                taskRemindAt = state.value.toDate.toEpochDay().toLocalDateTime(),
                                isDone = state.value.isTaskDone
                            ),
                        )
                        AgendaType.REMINDER -> reminderRepository.insertReminder(
                            AgendaItem.Reminder(
                                reminderId = state.value.id.ifEmpty { UUID.randomUUID().toString() },
                                reminderTitle = state.value.title,
                                reminderDescription = state.value.description,
                                reminderDate = state.value.fromDate.toEpochDay().toLocalDateTime(),
                                reminderRemindAt = state.value.toDate.toEpochDay().toLocalDateTime()
                            )
                        )
                        AgendaType.EVENT -> eventRepository.insertEvent(
                            AgendaItem.Event(
                                eventId = state.value.id.ifEmpty { UUID.randomUUID().toString() },
                                eventTitle = state.value.title,
                                eventDescription = state.value.description,
                                eventFromDate = state.value.fromDate.toEpochDay().toLocalDateTime(),
                                eventToDate = state.value.toDate.toEpochDay().toLocalDateTime(),
                                eventRemindAt = state.value.toDate.toEpochDay().toLocalDateTime()
                            )
                        )
                    }
                    _state.update { it.copy(shouldExit = true) }
                }
            }
            is AgendaDetailEvent.OnDelete -> {
                viewModelScope.launch(NonCancellable) {
                    when (state.value.agendaType) {
                        AgendaType.TASK -> taskRepository.deleteTaskById(state.value.id)
                        AgendaType.REMINDER -> reminderRepository.deleteReminderById(state.value.id)
                        AgendaType.EVENT -> eventRepository.deleteEventById(state.value.id)
                    }
                    _state.update { it.copy(shouldExit = true) }
                }
            }
            is AgendaDetailEvent.OnFromDateSelected -> {
                _state.update { it.copy(fromDate = event.fromDate) }
            }
            is AgendaDetailEvent.OnToDateSelected -> {
                _state.update { it.copy(toDate = event.toDate) }
            }
            is AgendaDetailEvent.OnTimeSelected -> {
                _state.update { it.copy(time = event.time) }
            }
            is AgendaDetailEvent.OnToTimeSelected -> {
                _state.update { it.copy(toTime = event.toTime) }
            }
            is AgendaDetailEvent.OnReminderTypeClick -> {
                _state.update { it.copy(isSelectingReminderType = true) }
            }
            is AgendaDetailEvent.OnReminderTypeDismiss -> {
                _state.update { it.copy(isSelectingReminderType = false) }
            }
            is AgendaDetailEvent.OnReminderTypeSelect -> {
                _state.update {
                    it.copy(
                        reminder = event.reminderType,
                        isSelectingReminderType = false
                    )
                }
            }
            is AgendaDetailEvent.OnUpdatedInformation -> {
                if (event.title.isNotBlank()) {
                    _state.update { it.copy(title = event.title ) }
                }
                if (event.description.isNotBlank()) {
                    _state.update { it.copy(description = event.description) }
                }
            }
            is AgendaDetailEvent.OnInitScreen -> {
                _state.update { it.copy(agendaType = event.agendaType) }
            }
            else -> { Unit }
        }
    }
}