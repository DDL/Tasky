package com.ddoczi.tasky.agenda.presentation.home.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import java.time.LocalDate

@Composable
fun HomeDayPicker(
    modifier: Modifier = Modifier,
    date: LocalDate,
    selectedDay: Int = 0,
    onDaySelected: (Int) -> Unit
){
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround
    ){
        for (i in 1..5){
            HomeDayPickerItem(
                day = date.plusDays(i.toLong()),
                isSelected = i == selectedDay,
                onDaySelected = { onDaySelected(i) }
            )
        }
    }
}

@Preview
@Composable
fun HomeDayPickerPreview() {
    HomeDayPicker(
        date = LocalDate.now(),
        selectedDay = 3,
        onDaySelected = {}
    )
}