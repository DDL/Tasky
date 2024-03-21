package com.ddoczi.tasky.agenda.presentation.detail.task

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class TaskDetailViewModel @Inject constructor(

): ViewModel() {
    private val _state = MutableStateFlow(TaskDetailState())
    val state = _state.asStateFlow()

    fun onEvent(event: TaskDetailEvent) {}
}