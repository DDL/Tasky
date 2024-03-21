package com.ddoczi.tasky.agenda.presentation.detail.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.NavigateNext
import androidx.compose.material.icons.outlined.NavigateNext
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ddoczi.tasky.R
import com.ddoczi.tasky.ui.theme.Black

@Composable
fun DetailDescription(
    modifier: Modifier = Modifier,
    description: String,
    isEditable: Boolean,
    onClick: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .then(if (isEditable) Modifier.clickable { onClick() } else Modifier)
            .padding(top = 20.dp, bottom = 20.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            modifier = Modifier.weight(9f),
            text = description,
            fontSize = 16.sp,
            color = Black
        )
        if (isEditable) {
            Icon(
                modifier = Modifier.weight(1f),
                imageVector = Icons.AutoMirrored.Outlined.NavigateNext,
                contentDescription = stringResource(id = R.string.edit_icon)
            )
        }
    }
}