package com.ddoczi.tasky.core.presentation.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun TaskyDropdown(
    items: List<String>,
    onItemSelected: (Int) -> Unit,
    showDropdown: Boolean,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        DropdownMenu(expanded = showDropdown, onDismissRequest = {
            onDismiss()
        }) {
            items.forEachIndexed { index, item ->
                TaskyDropdownItem(text = item, onClick = {
                    onItemSelected(index)
                    onDismiss()
                })
                if (index != items.size - 1) {
                    Divider()
                }
            }
        }
    }
}