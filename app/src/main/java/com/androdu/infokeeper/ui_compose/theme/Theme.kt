package com.androdu.infokeeper.ui_compose.theme

import android.app.Activity
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

// Define the dark color scheme for the theme
private val LightColorScheme = lightColorScheme(
    primary = ColorPrimary,
    secondary = ColorSecondary,
    tertiary = Pink40,
    background = White,
    outline = Outline
)

/**
 * Custom theme for the InfoKeeper app.
 *
 * Applies a dark color scheme with dynamic color support on Android S (API 31) and above.
 *
 * @param content The composable content to be styled with the theme.
 */
@Composable
fun InfoKeeperTheme(
    content: @Composable () -> Unit
) {
    val context = LocalContext.current

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            // Ensure the status bar icons are visible on the dark background.
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = true
        }
    }
    MaterialTheme(
        colorScheme = LightColorScheme,
        typography = Typography,
        content = content
    )
}