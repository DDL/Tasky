package com.ddoczi.tasky.agenda.presentation.detail.agenda

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
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
import com.ddoczi.tasky.agenda.enums.AgendaType
import com.ddoczi.tasky.agenda.presentation.detail.composables.DetailColor
import com.ddoczi.tasky.agenda.presentation.detail.composables.DetailDescription
import com.ddoczi.tasky.agenda.presentation.detail.composables.DetailHeader
import com.ddoczi.tasky.agenda.presentation.detail.composables.DetailNotificationReminder
import com.ddoczi.tasky.agenda.presentation.detail.composables.DetailTimeSelector
import com.ddoczi.tasky.agenda.presentation.detail.composables.DetailTitle
import com.ddoczi.tasky.core.presentation.composables.TaskyBackground
import com.ddoczi.tasky.ui.theme.Gray
import com.ddoczi.tasky.ui.theme.Green
import com.ddoczi.tasky.ui.theme.Light
import com.ddoczi.tasky.ui.theme.LightGreen
import java.time.LocalDate

@Composable
fun AgendaDetailScreen(
    state: AgendaDetailState,
    onEvent: (AgendaDetailEvent) -> Unit,
    agendaType: AgendaType = AgendaType.TASK
) {
    TaskyBackground(
        titleWeight = 1f,
        contentWeight = 9f,
        header = {
            DetailHeader(
                editingText = stringResource(R.string.edit_agenda, agendaType.name),
                date = state.date,
                onClose = { onEvent(AgendaDetailEvent.OnClose) },
                onEdit = { onEvent(AgendaDetailEvent.OnEdit) },
                onSave = { onEvent(AgendaDetailEvent.OnSave) },
            )
        }
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                DetailColor(
                    modifier = Modifier.padding(16.dp),
                    text = agendaType.name,
                    color = when (agendaType) {
                        AgendaType.TASK -> Green
                        AgendaType.REMINDER -> LightGreen
                        AgendaType.EVENT -> Gray
                        else -> Light
                    }
                )
                DetailTitle(
                    modifier = Modifier.padding(start = 16.dp),
                    title = state.title,
                    isEditable = state.isEditing,
                    onClick = { }
                )
                Divider(color = Light)
                DetailDescription(
                    modifier = Modifier.padding(start = 16.dp),
                    description = state.description,
                    isEditable = state.isEditing,
                    onClick = {  }
                )
                Divider(color = Light)
                DetailTimeSelector(
                    modifier = Modifier.padding(start = 16.dp),
                    text = stringResource(R.string.at),
                    date = state.date,
                    time = state.time,
                    isEditable = state.isEditing,
                    onDateSelected = { },
                    onTimeSelected = { }
                )
                Divider(color = Light)
                DetailNotificationReminder(
                    onClick = {},
                    showDropdown = false,
                    onDismiss = {},
                    isEditable = true
                )
            }
            Box {
                Divider(color = Light)
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp, bottom = 48.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        modifier = Modifier.clickable { onEvent(AgendaDetailEvent.OnDelete) },
                        text = stringResource(R.string.delete_agenda, agendaType.name).uppercase(),
                        color = Gray,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AgendaDetailScreenPreview() {
    AgendaDetailScreen(
        state = AgendaDetailState(
            title = "Title",
            description = "Description",
            date = LocalDate.now(),
            isEditing = true
        ),
        onEvent = {}
    )
}