package com.ddoczi.tasky.agenda.presentation.home

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Green
import androidx.compose.ui.graphics.Color.Companion.LightGray
import com.ddoczi.tasky.ui.theme.Light

sealed class HomeAgendaType(val name: String, val color: Color) {
    data object Event : HomeAgendaType("Event", LightGray)
    data object Task : HomeAgendaType("Task", Green)
    data object Reminder : HomeAgendaType("Reminder", Light)
}