package com.ddoczi.tasky.agenda.presentation.detail.agenda

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class AgendaDetailViewModel @Inject constructor(): ViewModel() {
    private val _state = MutableStateFlow(AgendaDetailState())
    val state = _state.asStateFlow()

    fun onEvent(event: AgendaDetailEvent) {
        when(event) {
            is AgendaDetailEvent.OnEdit -> {
                _state.update { it.copy(isEditing = true) }
            }
            is AgendaDetailEvent.OnSave -> {
                //save agenda by type
                _state.update { it.copy(shouldExit = true) }
            }
            is AgendaDetailEvent.OnDelete -> {
                //delete agenda by type
                _state.update { it.copy(shouldExit = true) }
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
            else -> { Unit }
        }
    }
}