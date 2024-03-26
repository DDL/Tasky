package com.ddoczi.tasky.agenda.presentation.editor

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.TextButton
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ddoczi.tasky.R
import com.ddoczi.tasky.ui.theme.Black
import com.ddoczi.tasky.ui.theme.Light
import com.ddoczi.tasky.ui.theme.Green

@Composable
fun AgendaEditorScreen(
    state: AgendaEditorState,
    onEvent: (AgendaEditorEvent) -> Unit,
    title: String,
    body: String
) {

//    The ID shouldn't be necessary here. You can see this as a generic text field screen
//    which returns the entered text to the previous screen once hit save
    LaunchedEffect(Unit) {
        onEvent(AgendaEditorEvent.OnLoad(title, body))
    }

    Column(modifier = Modifier.fillMaxSize()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, bottom = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconButton(onClick = { onEvent(AgendaEditorEvent.OnBack ) }) {
                Icon(
                    imageVector = Icons.Default.ArrowBackIosNew,
                    contentDescription = stringResource(R.string.back_icon)
                )
            }
            Text(
                text = state.title.uppercase(),
                fontSize = 18.sp,
                color = Black,
                fontWeight = FontWeight.SemiBold
            )
            TextButton(
                onClick = { onEvent(AgendaEditorEvent.OnSave(state.title, state.body)) },
                colors = ButtonDefaults.textButtonColors(contentColor = Green)
            ) {
                Text(text = stringResource(id = R.string.save).uppercase())
            }
        }
        Divider(color = Light, modifier = Modifier.padding(start = 16.dp, end = 16.dp))
        Spacer(modifier = Modifier.height(30.dp))
        BasicTextField(
            value = state.body,
            modifier = Modifier.padding(start = 16.dp, end = 16.dp),
            onValueChange = { onEvent(AgendaEditorEvent.OnTextChange(it)) },
            textStyle = TextStyle(
                color = Black,
                fontSize = if(state.title == stringResource(R.string.edit_title)) 26.sp else 18.sp
            )
        )
    }
}