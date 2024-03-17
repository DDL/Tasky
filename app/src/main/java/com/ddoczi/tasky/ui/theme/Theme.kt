package com.ddoczi.tasky.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.graphics.Color
import com.vanpra.composematerialdialogs.datetime.date.DatePickerColors

private val LightColorScheme = lightColorScheme(
    primary = Black,
    secondary = White,
    background = White,
    surface = White,
    onPrimary = White,
    onSecondary = Black,
    onBackground = Black,
    onSurface = Black,
)

val datePickerColors = object : DatePickerColors {
    override val calendarHeaderTextColor: Color = Black
    override val headerBackgroundColor: Color = Black
    override val headerTextColor: Color = White

    @Composable
    override fun dateBackgroundColor(active: Boolean): State<Color> {
        return rememberUpdatedState(if (active) Black else White)
    }

    @Composable
    override fun dateTextColor(active: Boolean): State<Color> {
        return rememberUpdatedState(if (active) White else Black)
    }

}

@Composable
fun TaskyTheme(content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = LightColorScheme,
        typography = Typography,
        content = content,
        shapes = shapes
    )
}