package com.ddoczi.tasky.agenda.presentation.detail.agenda

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class AgendaDetailViewModel @Inject constructor(): ViewModel() {
    private val _state = MutableStateFlow(AgendaDetailState())
    val state = _state.asStateFlow()

    fun onEvent(event: AgendaDetailEvent) {}
}