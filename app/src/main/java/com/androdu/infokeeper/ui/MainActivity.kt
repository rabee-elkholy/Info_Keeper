package com.androdu.infokeeper.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.androdu.infokeeper.databinding.ActivityMainBinding

/**
 * Main activity for the InfoKeeper app that serves as the entry point for navigating to different UI modes.
 *
 * This activity provides options for:
 * - Opening the Compose-based UI by starting [ComposeActivity].
 * - Opening the view-based UI by starting [ViewsActivity].
 *
 * It initializes the layout and sets up click listeners for buttons to launch the respective activities.
 */
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflate the layout and set it as the content view.
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize click listeners for buttons.
        initClicks()
    }

    /**
     * Sets up click listeners for buttons to start different activities.
     *
     * - [openComposeButton]: Starts [ComposeActivity] which contains the Compose-based UI.
     * - [openViewsButton]: Starts [ViewsActivity] which contains the view-based UI.
     */
    private fun initClicks() {
        binding.openComposeButton.setOnClickListener {
            // Intent to start ComposeActivity.
            val intent = Intent(this, ComposeActivity::class.java)
            startActivity(intent)
        }

        binding.openViewsButton.setOnClickListener {
            // Intent to start ViewsActivity.
            val intent = Intent(this, ViewsActivity::class.java)
            startActivity(intent)
        }
    }
}
