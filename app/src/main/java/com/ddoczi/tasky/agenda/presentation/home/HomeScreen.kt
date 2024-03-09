package com.ddoczi.tasky.agenda.presentation.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.ddoczi.tasky.agenda.presentation.home.composables.HomeHeader
import com.ddoczi.tasky.core.presentation.composables.TaskyBackground
import com.ddoczi.tasky.ui.theme.TaskyTheme
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import java.time.LocalDate

@Composable
fun HomeScreen(
    state: HomeState,
    onEvent: (HomeEvent) -> Unit
) {
    val datePickerState = rememberMaterialDialogState()
    MaterialDialog(
        dialogState = datePickerState,
        buttons = {
            positiveButton("Ok")
            negativeButton("Cancel")
        }
    ) {

    }

    TaskyBackground(
        titleWeight = 1f,
        contentWeight = 9f,
        header = {
            HomeHeader(
                date = LocalDate.now(),
                name = "test",
                onMonthClick = { datePickerState.show() },
                onProfileClick = { onEvent(HomeEvent.OnLogOut)
        }) }
    ) {

        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "No Agenda items to show",
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    TaskyTheme {
        HomeScreen(
            state = HomeState(),
            onEvent = {}
        )
    }
}
