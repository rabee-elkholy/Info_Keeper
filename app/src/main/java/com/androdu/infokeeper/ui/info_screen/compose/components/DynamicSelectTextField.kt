package com.androdu.infokeeper.ui.info_screen.compose.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.androdu.infokeeper.R
import com.androdu.infokeeper.domain.utils.Gender
import com.androdu.infokeeper.domain.utils.JobTitle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T> DynamicSelectTextField(
    selectedValue: T,
    options: List<T>,
    label: String,
    onValueChangedEvent: (T) -> Unit,
    isError: Boolean,
    supportingText: String,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded },
        modifier = modifier
    ) {
        OutlinedTextField(
            readOnly = true,
            value = stringResource(id = getOptionNameRes(selectedValue)),
            onValueChange = {},
            label = { Text(text = label) },
            trailingIcon = {
                Icon(
                    painter = painterResource(if (expanded) R.drawable.arrow_up else R.drawable.arrow_down),
                    contentDescription = null
                )
            },
            colors = OutlinedTextFieldDefaults.colors(),
            isError = isError,
            supportingText = {
                if (isError)
                    Text(
                        text = supportingText,
                        color = MaterialTheme.colorScheme.error
                    )
            },
            modifier = Modifier
                .menuAnchor()
                .fillMaxWidth()
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            options.forEach { option: T ->
                DropdownMenuItem(
                    text = { Text(text = stringResource(id = getOptionNameRes(option))) },
                    onClick = {
                        expanded = false
                        onValueChangedEvent(option)
                    }
                )
            }
        }
    }
}

/**
 * Retrieves the string resource ID for the option name based on its type.
 *
 * @param option The option for which to get the string resource ID.
 * @return The string resource ID corresponding to the option name.
 */
private fun <T> getOptionNameRes(option: T): Int = when (option) {
    is JobTitle -> option.getStringRes()
    is Gender -> option.getStringRes()
    else -> R.string.empty
}
