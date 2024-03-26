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
import com.ddoczi.tasky.core.util.toLocalDateTime
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
    savedStateHandle: SavedStateHandle,
): ViewModel() {
    private val _state = MutableStateFlow(AgendaDetailState())
    val state = _state.asStateFlow()

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
                                eventId = event?.eventId  ?: "",
                                eventTitle = event?.eventTitle ?: "New Agenda",
                                eventDescription = event?.eventDescription ?: "Description",
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
                                taskId = task?.taskId ?: "",
                                taskTitle = task?.taskTitle ?: "New Agenda",
                                taskDescription = task?.taskDescription ?: "Description",
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
                            reminderId = reminder?.reminderId ?: "",
                            reminderTitle = reminder?.reminderTitle ?: "New Agenda",
                            reminderDescription = reminder?.reminderDescription ?: "Description",
                            reminderDate = reminder?.reminderDate ?: LocalDateTime.now(),
                            reminderRemindAt = reminder?.reminderRemindAt ?: LocalDateTime.now()
                        )
                    )
                }
            }
            else -> { Unit }
        }
    }

//    When you then update let’s say the title, you can update the title of the agenda item state:
//    state = state.copy(
//        agendaItem = itemAsEvent()?.copy(title = newTitle)
//    )
//    itemAsEvent would just be a helper function to avoid having to cast the item all the time
//    (so just a function that returns state.agendaItem as? AgendaItem.Event

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
                    // Handle error
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
                    // Handle error
                }
            }
            is AgendaDetailEvent.OnFromDateSelected -> {
                //when
                _state.update { it.copy(fromDate = event.fromDate) }
            }
            is AgendaDetailEvent.OnToDateSelected -> {
                //event
                _state.update { it.copy(toDate = event.toDate) }
            }
            is AgendaDetailEvent.OnTimeSelected -> {
                //
                _state.update { it.copy(time = event.time) }
            }
            is AgendaDetailEvent.OnToTimeSelected -> {
                //
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
                //when
                if (event.title.isNotBlank()) {
                    _state.update { it.copy(title = event.title ) }
                }
                if (event.description.isNotBlank()) {
                    _state.update { it.copy(description = event.description) }
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