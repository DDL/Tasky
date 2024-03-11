package com.ddoczi.tasky.agenda.presentation.home.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ddoczi.tasky.ui.theme.Black
import com.ddoczi.tasky.ui.theme.Gray
import com.ddoczi.tasky.ui.theme.Orange
import java.time.LocalDate

@Composable
fun HomeDayPickerItem(
    modifier: Modifier = Modifier,
    day: LocalDate,
    isSelected: Boolean = false,
    onDaySelected: () -> Unit
){
    Column(modifier = modifier
        .clip(RoundedCornerShape(100))
        .background(if (isSelected) Orange else Color.Transparent)
        .clickable { onDaySelected() }
        .padding(8.dp),
        horizontalAlignment  = Alignment.CenterHorizontally
    ) {
        Text(text = day.dayOfWeek.name[0].toString(),
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = if (isSelected) Black else Gray
        )
        Text(text = day.dayOfMonth.toString(),
            fontSize = 17.sp,
            fontWeight = FontWeight.Bold,
            color = Black
        )
    }
}

@Preview
@Composable
fun HomeDayPickerItemPreview() {
    HomeDayPickerItem(
        day = LocalDate.now(),
        isSelected = true,
        onDaySelected = {}
    )
}


