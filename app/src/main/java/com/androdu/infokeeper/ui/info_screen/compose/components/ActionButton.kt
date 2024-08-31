package com.androdu.infokeeper.ui.info_screen.compose.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * A custom action button with an optional loading indicator.
 *
 * @param modifier A [Modifier] for adjusting the layout or behavior of the button.
 * @param text The text to display on the button.
 * @param enabled Whether the button is enabled for interaction. Default is `true`.
 * @param isLoading Whether to show a loading indicator on the button. Default is `false`.
 * @param onClick The callback to be invoked when the button is clicked.
 */
@Composable
fun ActionButton(
    modifier: Modifier = Modifier,
    text: String,
    enabled: Boolean = true,
    isLoading: Boolean = false,
    onClick: () -> Unit
) {
    val progressAlpha by animateFloatAsState(targetValue = if (isLoading) 1f else 0f, label = "Progress alpha animation")
    val textAlpha by animateFloatAsState(targetValue = if (isLoading) 0f else 1f, label = "Text alpha animation")

    Button(
        modifier = modifier.height(IntrinsicSize.Min),
        enabled = enabled,
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary
        ),
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = 4.dp,
        ),
        shape = RoundedCornerShape(size = 100f),
        onClick = onClick
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(
                modifier = Modifier
                    .size(24.dp)
                    .alpha(alpha = progressAlpha),
                color = MaterialTheme.colorScheme.onPrimary,
                strokeWidth = 1.5.dp
            )
            Text(
                text = text,
                modifier = Modifier.alpha(alpha = textAlpha),
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            )
        }
    }
}

@Preview
@Composable
private fun ActionButtonPreview() {
    ActionButton(
        text = "Save",
        onClick = {}
    )
}