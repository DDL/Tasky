package com.ddoczi.tasky.agenda.presentation.detail.composables

import androidx.compose.foundation.clickable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.ddoczi.tasky.R
import com.ddoczi.tasky.core.presentation.composables.TaskyHeader
import java.time.LocalDate
import java.time.LocalDateTime

@Composable
fun DetailHeader(
    modifier: Modifier = Modifier,
    editingText: String,
    date: LocalDate,
    onClose: () -> Unit,
    onEdit: () -> Unit,
    onSave: () -> Unit,
    isEditing: Boolean = false
) {
    TaskyHeader(modifier = modifier) {
        IconButton(onClick = { onClose() }) {
            Icon(
                imageVector = Icons.Default.Close,
                contentDescription = stringResource(R.string.close)
            )
        }
        Text(
            text = if (isEditing) editingText.uppercase()
                   else "${date.dayOfMonth} ${date.month.name} ${date.year}",
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold
        )
        if(isEditing) {
            Text(
                modifier = modifier.clickable { onSave() },
                text = stringResource(R.string.save),
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold
            )
        } else {
            IconButton(onClick = { onEdit() }) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = stringResource(R.string.edit_icon)
                )
            }
        }
    }
}