package com.androdu.infokeeper.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.androdu.infokeeper.databinding.ActivityViewsBinding

/**
 * Activity for displaying the view-based UI in the InfoKeeper app.
 *
 * This activity sets up the layout using view-based components, as defined in [ActivityViewsBinding].
 * It initializes the layout and sets it as the content view for this activity.
 */
class ViewsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityViewsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflate the layout and set it as the content view.
        binding = ActivityViewsBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
