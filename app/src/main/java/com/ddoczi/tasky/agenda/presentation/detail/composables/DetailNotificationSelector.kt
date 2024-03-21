package com.ddoczi.tasky.agenda.presentation.detail.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.NavigateNext
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.DarkGray
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ddoczi.tasky.R
import com.ddoczi.tasky.core.presentation.composables.TaskyDropdown
import com.ddoczi.tasky.ui.theme.Black
import com.ddoczi.tasky.ui.theme.Gray

@Composable
fun DetailNotificationReminder(
    showDropdown: Boolean,
    onClick: () -> Unit,
    onDismiss: () -> Unit,
    isEditable: Boolean,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .then(
                if (isEditable) {
                    Modifier.clickable {
                        onClick()
                    }
                } else Modifier
            )
            .padding(top = 20.dp, bottom = 20.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier.background(Gray, shape = RoundedCornerShape(5.dp))
            ) {
                Icon(
                    imageVector = Icons.Default.Notifications,
                    tint = DarkGray,
                    contentDescription = "notification",
                    modifier = Modifier.padding(2.dp)
                )
            }
            Spacer(modifier = Modifier.width(13.dp))
            Text(
                text = "TEST",
                fontSize = 16.sp,
                color = Black
            )
        }
        if (isEditable) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.NavigateNext,
                contentDescription = stringResource(R.string.edit_icon)
            )
        }
    }
    TaskyDropdown(
        items = listOf(
            "10 minutes before",
            "30 minutes before",
            "1 hour before",
            "6 hours before",
            "1 day before)",
        ),
        onItemSelected = {},
        onDismiss = onDismiss,
        modifier = modifier,
        showDropdown = showDropdown
    )
}