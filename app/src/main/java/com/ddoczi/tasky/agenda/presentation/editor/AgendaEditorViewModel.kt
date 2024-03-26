package com.ddoczi.tasky.agenda.presentation.editor

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class AgendaEditorViewModel @Inject constructor() : ViewModel() {
    private val _state = MutableStateFlow(AgendaEditorState())
    val state = _state.asStateFlow()

    fun onEvent(event: AgendaEditorEvent) {
        when(event) {
            is AgendaEditorEvent.OnLoad -> {
                _state.update {
                    it.copy(
                        title = event.title,
                        body = event.body
                    )
                }
            }
            is AgendaEditorEvent.OnTextChange -> {
                _state.update { it.copy(body = event.text) }
            }
            else -> { Unit }
        }
    }
}