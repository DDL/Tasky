package com.ddoczi.tasky.core.presentation.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TaskyDropdownItem(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
) {
    Box(
        modifier = modifier.fillMaxWidth().clickable {
            onClick()
        }.padding(start = 20.dp, top = 20.dp, bottom = 20.dp, end = 104.dp)
    ) {
        Text(text = text, fontSize = 16.sp)
    }
}