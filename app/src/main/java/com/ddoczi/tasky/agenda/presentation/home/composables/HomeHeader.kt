package com.ddoczi.tasky.agenda.presentation.home.composables

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ddoczi.tasky.core.presentation.composables.TaskyHeader
import java.time.LocalDate

@Composable
fun HomeHeader(
    date: LocalDate,
    name: String,
    onMonthClick: () -> Unit,
    onProfileClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    TaskyHeader(
        modifier = modifier
    ) {
        HomeHeaderMonthPicker(
            date = date,
            onMonthClick = onMonthClick
        )
        HomeHeaderProfileName(
            name = name,
            onProfileClick = onProfileClick
        )
    }
}