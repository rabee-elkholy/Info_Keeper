package com.androdu.infokeeper.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.androdu.infokeeper.ui.theme.InfoKeeperTheme

/**
 * The main activity for the InfoKeeper app, which sets up the Compose UI.
 *
 * This activity is responsible for:
 * - Enabling edge-to-edge display to maximize screen utilization.
 * - Setting the Compose UI content using the [InfoKeeperTheme].
 * - Displaying the main navigation graph using [AppNavHost].
 */
class ComposeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge() // Enables edge-to-edge display mode for better screen utilization.
        setContent {
            InfoKeeperTheme { // Applies the theme defined for the app.
                Surface(
                    modifier = Modifier.fillMaxSize(), // Ensures the surface occupies the full screen.
                    color = MaterialTheme.colorScheme.background // Sets the background color from the theme.
                ) {
                    AppNavHost(navController = rememberNavController()) // Sets up the navigation graph.
                }
            }
        }
    }
}
