package com.ddoczi.tasky.agenda.presentation.home.composables

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.ddoczi.tasky.core.presentation.composables.TaskyHeader
import java.time.LocalDate

@Composable
fun HomeHeader(
    modifier: Modifier = Modifier,
    date: LocalDate,
    name: String,
    onMonthClick: () -> Unit,
    onProfileClick: () -> Unit,
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

@Preview
@Composable
fun HomeHeaderPreview() {
    HomeHeader(
        date = LocalDate.now(),
        name = "DD",
        onMonthClick = {},
        onProfileClick = {}
    )
}