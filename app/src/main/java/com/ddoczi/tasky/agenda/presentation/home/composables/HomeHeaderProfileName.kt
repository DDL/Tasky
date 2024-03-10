package com.ddoczi.tasky.agenda.presentation.home.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ddoczi.tasky.ui.theme.Light
import com.ddoczi.tasky.ui.theme.LightBlue

@Composable
fun HomeHeaderProfileName(
    name: String,
    onProfileClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .background(Light, shape = CircleShape)
            .clickable { onProfileClick() }
            .padding(8.dp)
    ) {
        Text(
            text = name,
            color = LightBlue,
            fontSize = 13.sp,
            fontWeight = FontWeight.SemiBold
        )
    }
}