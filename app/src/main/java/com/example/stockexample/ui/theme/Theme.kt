package com.example.stockexample.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    primary = DarkGrey,
    secondary = LightGrey,
    tertiary = LightBlue,
    background = DarkGrey,
    onBackground = Color.White,
    surface = DarkGrey,
    onSurface = Color.White,
    onSurfaceVariant = LightGrey,
    error = ErrorRed,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onTertiary = Color.White,
)

@Composable
fun StockExampleTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = DarkColorScheme,
        typography = Typography(),
        content = content
    )
}