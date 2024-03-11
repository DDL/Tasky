package com.ddoczi.tasky.agenda.presentation.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ddoczi.tasky.R
import com.ddoczi.tasky.agenda.presentation.home.composables.HomeDayPicker
import com.ddoczi.tasky.agenda.presentation.home.composables.HomeHeader
import com.ddoczi.tasky.core.presentation.composables.TaskyBackground
import com.ddoczi.tasky.core.presentation.composables.TaskyButton
import com.ddoczi.tasky.core.presentation.composables.TaskyDropdown
import com.ddoczi.tasky.ui.theme.TaskyTheme
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.datetime.date.datepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun HomeScreen(
    state: HomeState,
    onEvent: (HomeEvent) -> Unit
) {
    val datePickerState = rememberMaterialDialogState()
    MaterialDialog(
        dialogState = datePickerState,
        buttons = {
            positiveButton(stringResource(R.string.ok))
            negativeButton(stringResource(R.string.cancel))
        }
    ) {
        datepicker { date ->
            onEvent(HomeEvent.OnDateSelected(date))
        }
    }

    TaskyBackground(
        titleWeight = 1f,
        contentWeight = 9f,
        header = {
            HomeHeader(
                date = LocalDate.now(),
                name = "test",
                onMonthClick = { datePickerState.show() },
                onProfileClick = { onEvent(HomeEvent.OnLogOut) }
            )
            Box(modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                TaskyDropdown(
                    items = listOf("Log out"),
                    onItemSelected = { },
                    showDropdown = state.showLogout,
                    onDismiss = { onEvent(HomeEvent.OnLogOutDismiss) })
            }
        },
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            HomeDayPicker(date = state.currentDate, selectedDay = state.selectedDay) {
                onEvent(HomeEvent.OnDaySelected(it))
            }
            val date = state.currentDate.plusDays(state.selectedDay.toLong())
            val dateTitle = if (date == LocalDate.now()) {
                "Today"
            } else {
                val dateFormatter = DateTimeFormatter.ofPattern("dd MMMM yyyy")
                date.format(dateFormatter)
            }
            Text(
                text = dateTitle,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 16.dp)
            )
            Spacer(modifier = Modifier.height(24.dp))
            LazyColumn(modifier = Modifier.fillMaxWidth()){
                items(state.agendaItems) {
                    //create HomeAgendaItem composable
                }
            }
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomEnd) {
                TaskyButton(text = "+", onClick = {})
                TaskyDropdown(
                    items = listOf("Add"),
                    onItemSelected = {},
                    onDismiss = { },
                    showDropdown = false
                )
            }
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
