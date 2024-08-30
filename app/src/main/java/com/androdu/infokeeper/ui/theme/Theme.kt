package com.androdu.infokeeper.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

// Define the dark color scheme for the theme
private val DarkColorScheme = darkColorScheme(
    primary = Green,
    background = Black,
    surface = DarkGray,
    secondary = White,
    tertiary = White,
    primaryContainer = Green30,
    onPrimary = Black,
    onBackground = White,
    onSurface = White,
    onSurfaceVariant = Gray
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
    val colorScheme = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        dynamicDarkColorScheme(context)
    } else {
        // Apply side effects related to the theme if not in edit mode.
        val view = LocalView.current
        if (!view.isInEditMode) {
            SideEffect {
                val window = (view.context as Activity).window
                // Ensure the status bar icons are visible on the dark background.
                WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = false
            }
        }
        DarkColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}