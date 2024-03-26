package com.ddoczi.tasky.agenda.presentation.editor

sealed class AgendaEditorEvent {
    data class OnLoad(val title: String, val body: String) : AgendaEditorEvent()
    data class OnTextChange(val text: String) : AgendaEditorEvent()
    data class OnSave(val text: String, val body: String) : AgendaEditorEvent()
    data object OnBack : AgendaEditorEvent()
}