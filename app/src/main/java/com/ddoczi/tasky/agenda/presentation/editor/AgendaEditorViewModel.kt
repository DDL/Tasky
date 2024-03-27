package com.ddoczi.tasky.agenda.presentation.editor

import androidx.lifecycle.SavedStateHandle
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class AgendaEditorViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _state = MutableStateFlow(AgendaEditorState())
    val state = _state.asStateFlow()

    init {
        _state.update {
            it.copy(
                savedStateHandle.get("title") ?: "",
                savedStateHandle.get("decription") ?: ""
            )
        }
    }

    fun onEvent(event: AgendaEditorEvent) {
        when(event) {
            is AgendaEditorEvent.OnTextChange -> {
                _state.update { it.copy(body = event.text) }
            }
            else -> { Unit }
        }
    }
}