package com.ddoczi.tasky.agenda.presentation.home.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.material.icons.outlined.CheckCircleOutline
import androidx.compose.material.icons.outlined.Circle
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.DarkGray
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ddoczi.tasky.agenda.domain.model.AgendaItem
import com.ddoczi.tasky.ui.theme.Black
import com.ddoczi.tasky.ui.theme.Green
import com.ddoczi.tasky.ui.theme.White
import java.time.LocalDateTime

@Composable
fun HomeAgendaItem(
    modifier: Modifier = Modifier,
    item: AgendaItem,
    color: Color,
    onItemOptionsClick: (AgendaItem) -> Unit,
    onItemClick: (AgendaItem) -> Unit
) {
    val textColor = if (item is AgendaItem.Task) White else Black
    val descriptionColor = if (item is AgendaItem.Task) White else DarkGray

    Column(modifier = modifier
        .fillMaxWidth()
        .heightIn(min = 130.dp)
        .background(
            color = color,
            shape = RoundedCornerShape(20.dp)
        )
        .padding(16.dp)
    ) {
        Row(modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(modifier = if (item is AgendaItem.Task) Modifier.weight(9f)
                           else Modifier
                .clickable { onItemClick(item) }
                .weight(9f),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = if (item is AgendaItem.Task && item.isDone) Icons.Outlined.CheckCircleOutline
                                  else Icons.Outlined.Circle,
                    contentDescription = "title icon",
                    tint = textColor
                )
                Spacer(modifier = modifier.width(12.dp))
                Text(
                    text = item.title,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = textColor,
                    textDecoration = if (item is AgendaItem.Task && item.isDone) TextDecoration.LineThrough else null
                )
            }
            IconButton(
                modifier = Modifier.weight(1f),
                onClick = { onItemOptionsClick(item) }
            ) {
                Icon(
                    imageVector = Icons.Default.MoreHoriz,
                    contentDescription = "options icon",
                    tint = textColor
                )
            }
        }
        Text(
            modifier = Modifier.padding(start = 36.dp),
            text = item.description,
            fontSize = 14.sp,
            color = descriptionColor
        )
        Spacer(modifier = modifier.height(16.dp))
        Text(
            modifier = Modifier.align(Alignment.End),
            text = item.time.toString(),
            fontSize = 14.sp,
            color = descriptionColor
        )
    }
}

@Preview
@Composable
fun HomeAgendaItemPreview() {
    HomeAgendaItem(
        item = AgendaItem.Task(
            taskId = "1",
            taskTitle = "Task 1",
            taskDescription = "Task 1 description",
            taskDate = LocalDateTime.now(),
            taskRemindAt = LocalDateTime.now(),
            isDone = true
        ),
        color = Green,
        onItemOptionsClick = {},
        onItemClick = {}
    )
}