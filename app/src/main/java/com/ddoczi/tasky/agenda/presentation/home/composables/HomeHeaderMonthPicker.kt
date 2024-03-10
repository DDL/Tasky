package com.ddoczi.tasky.agenda.presentation.home.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ddoczi.tasky.R
import com.ddoczi.tasky.ui.theme.White
import java.time.LocalDate

@Composable
fun HomeHeaderMonthPicker(
    date: LocalDate,
    onMonthClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .clickable { onMonthClick() }
            .padding(4.dp)
    ) {
        Text(
            text = date.month.name,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            color = White
        )
        Spacer(modifier = Modifier.width(5.dp))
        Icon(
            imageVector = Icons.Default.ArrowDropDown,
            tint = White,
            contentDescription = stringResource(R.string.month_picker_dropdown_icon)
        )
    }
}