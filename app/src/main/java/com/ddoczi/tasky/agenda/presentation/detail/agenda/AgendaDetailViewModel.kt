package com.ddoczi.tasky.agenda.presentation.detail.agenda

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ddoczi.tasky.agenda.domain.model.AgendaItem
import com.ddoczi.tasky.agenda.domain.repository.EventRepository
import com.ddoczi.tasky.agenda.domain.repository.ReminderRepository
import com.ddoczi.tasky.agenda.domain.repository.TaskRepository
import com.ddoczi.tasky.agenda.enums.AgendaOption
import com.ddoczi.tasky.agenda.enums.AgendaType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.NonCancellable
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class AgendaDetailViewModel @Inject constructor(
    private val eventRepository: EventRepository,
    private val taskRepository: TaskRepository,
    private val reminderRepository: ReminderRepository,
    private val savedStateHandle: SavedStateHandle,
): ViewModel() {
    private val _state = MutableStateFlow(AgendaDetailState())
    val state = _state.asStateFlow()

    private val defaultTitle = "New Agenda"
    private val defaultDescription = "Description"

    private fun itemAsTask(): AgendaItem.Task? {
        return state.value.agendaItem as? AgendaItem.Task
    }

    private fun itemAsEvent(): AgendaItem.Event? {
        return state.value.agendaItem as? AgendaItem.Event
    }

    private fun itemAsReminder(): AgendaItem.Reminder? {
        return state.value.agendaItem as? AgendaItem.Reminder
    }

    init {
        savedStateHandle.get<AgendaType>("agendaType")?.let {
            onEvent(AgendaDetailEvent.OnInitScreen(it, savedStateHandle.get("agendaOption")))
        }
        val agendaId = savedStateHandle.get<String>("id")
        when(savedStateHandle.get<AgendaType>("agendaType")) {
            AgendaType.EVENT ->
                viewModelScope.launch {
                    val event = if (agendaId != null) eventRepository.getEventById(agendaId) else null
                    _state.update {
                        it.copy(
                            agendaItem = AgendaItem.Event(
                                eventId = event?.eventId  ?: UUID.randomUUID().toString(),
                                eventTitle = event?.eventTitle ?: defaultTitle,
                                eventDescription = event?.eventDescription ?: defaultDescription,
                                eventFromDate = event?.eventFromDate ?: LocalDateTime.now(),
                                eventToDate = event?.eventToDate ?: LocalDateTime.now(),
                                eventRemindAt = event?.eventRemindAt ?: LocalDateTime.now()
                            )
                        )
                    }
                }
            AgendaType.TASK ->
                viewModelScope.launch {
                    val task = if (agendaId != null) taskRepository.getTaskById(agendaId) else null
                    _state.update {
                        it.copy(
                            agendaItem = AgendaItem.Task(
                                taskId = task?.taskId ?: UUID.randomUUID().toString(),
                                taskTitle = task?.taskTitle ?: defaultTitle,
                                taskDescription = task?.taskDescription ?: defaultDescription,
                                taskDate = task?.taskDate ?: LocalDateTime.now(),
                                taskRemindAt = task?.taskRemindAt ?: LocalDateTime.now(),
                                isDone = task?.isDone ?: false
                            )
                        )
                    }
                }
            AgendaType.REMINDER -> viewModelScope.launch {
                val reminder = if (agendaId != null) reminderRepository.getReminderById(agendaId) else null
                _state.update {
                    it.copy(
                        agendaItem = AgendaItem.Reminder(
                            reminderId = reminder?.reminderId ?: UUID.randomUUID().toString(),
                            reminderTitle = reminder?.reminderTitle ?: defaultTitle,
                            reminderDescription = reminder?.reminderDescription ?: defaultDescription,
                            reminderDate = reminder?.reminderDate ?: LocalDateTime.now(),
                            reminderRemindAt = reminder?.reminderRemindAt ?: LocalDateTime.now()
                        )
                    )
                }
            }
            else -> { Unit }
        }
    }

    fun onEvent(event: AgendaDetailEvent) {
        when(event) {
            is AgendaDetailEvent.OnEdit -> {
                _state.update { it.copy(isEditing = true) }
            }
            is AgendaDetailEvent.OnSave -> {
                try {
                    _state.update { it.copy(isEditing = false) }
                    viewModelScope.launch {
                        when (state.value.agendaType) {
                            AgendaType.TASK -> taskRepository.insertTask(state.value.agendaItem as AgendaItem.Task)
                            AgendaType.REMINDER -> reminderRepository.insertReminder(state.value.agendaItem as AgendaItem.Reminder)
                            AgendaType.EVENT -> eventRepository.insertEvent(state.value.agendaItem as AgendaItem.Event)
                        }
                        _state.update { it.copy(shouldExit = true) }
                    }
                } catch (e: Exception) {
                    println(e)
                }
            }
            is AgendaDetailEvent.OnDelete -> {
                try {
                    viewModelScope.launch(NonCancellable) {
                        if (state.value.agendaItem?.agendaItemId?.isEmpty() == true) return@launch
                        when (state.value.agendaType) {
                            AgendaType.TASK -> state.value.agendaItem?.let {
                                taskRepository.deleteTaskById(
                                    it.agendaItemId)
                            }
                            AgendaType.REMINDER -> state.value.agendaItem?.let {
                                reminderRepository.deleteReminderById(
                                    it.agendaItemId)
                            }
                            AgendaType.EVENT -> state.value.agendaItem?.let {
                                eventRepository.deleteEventById(
                                    it.agendaItemId)
                            }
                        }
                        _state.update { it.copy(shouldExit = true) }
                    }
                } catch (e: Exception) {
                    println(e)
                }
            }
            is AgendaDetailEvent.OnFromDateSelected -> {
                _state.update {
                    it.copy(
                        fromDate = event.fromDate,
                        agendaItem = when(state.value.agendaType) {
                            AgendaType.EVENT -> itemAsEvent()?.copy(eventFromDate = event.fromDate.atTime(state.value.time))
                            AgendaType.REMINDER -> itemAsReminder()?.copy(reminderDate = event.fromDate.atTime(state.value.time))
                            AgendaType.TASK -> itemAsTask()?.copy(taskDate = event.fromDate.atTime(state.value.time))
                        }
                    )
                }
            }
            is AgendaDetailEvent.OnToDateSelected -> {
                _state.update {
                    it.copy(
                        toDate = event.toDate,
                        agendaItem = itemAsEvent()?.copy(eventToDate = event.toDate.atTime(state.value.time))
                    )
                }
            }
            is AgendaDetailEvent.OnTimeSelected -> {
                _state.update {
                    it.copy(
                        time = event.time,
                        agendaItem = when(state.value.agendaType) {
                            AgendaType.EVENT -> itemAsEvent()?.copy(eventFromDate = state.value.fromDate.atTime(event.time))
                            AgendaType.REMINDER -> itemAsReminder()?.copy(reminderDate = state.value.fromDate.atTime(event.time))
                            AgendaType.TASK -> itemAsTask()?.copy(taskDate = state.value.fromDate.atTime(event.time))
                        }
                    )
                }
            }
            is AgendaDetailEvent.OnToTimeSelected -> {
                _state.update {
                    it.copy(
                        toTime = event.toTime,
                        agendaItem = itemAsEvent()?.copy(eventToDate = state.value.toDate.atTime(event.toTime))
                    )
                }
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
                    _state.update {
                        it.copy(
                            agendaItem = when(state.value.agendaType) {
                                AgendaType.EVENT -> itemAsEvent()?.copy(eventTitle = event.title)
                                AgendaType.REMINDER -> itemAsReminder()?.copy(reminderTitle = event.title)
                                AgendaType.TASK -> itemAsTask()?.copy(taskTitle = event.title)
                            }
                        )
                    }
                }
                if (event.description.isNotBlank()) {
                    _state.update {
                        it.copy(
                            agendaItem = when(state.value.agendaType) {
                                AgendaType.EVENT -> itemAsEvent()?.copy(eventDescription = event.description)
                                AgendaType.REMINDER -> itemAsReminder()?.copy(reminderDescription = event.description)
                                AgendaType.TASK -> itemAsTask()?.copy(taskDescription = event.description)
                            }
                        )
                    }
                }
            }
            is AgendaDetailEvent.OnInitScreen -> {
                _state.update {
                    it.copy(
                        agendaType = event.agendaType,
                        isEditable = event.agendaOption != AgendaOption.OPEN,
                    )
                }
            }
            else -> { Unit }
        }
    }
}