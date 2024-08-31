package com.androdu.infokeeper.ui_compose.info_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.androdu.infokeeper.R
import com.androdu.infokeeper.domain.utils.Gender
import com.androdu.infokeeper.domain.utils.JobTitle
import com.androdu.infokeeper.ui_compose.info_screen.components.ActionButton
import com.androdu.infokeeper.ui_compose.info_screen.components.DynamicSelectTextField
import com.androdu.infokeeper.ui_compose.theme.InfoKeeperTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun InfoScreenRoot(
    onUserSaved: () -> Unit,
    viewModel: InfoViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsState()
    InfoScreenContent(
        state = state,
        onAction = viewModel::onAction,
        onUserSaved = onUserSaved
    )
}

@Composable
fun InfoScreenContent(
    state: InfoScreenState,
    onAction: (InfoScreenAction) -> Unit,
    onUserSaved: () -> Unit
) {
    val validationState = state.infoValidationState
    val scrollState = rememberScrollState()

    var name by remember { mutableStateOf("") }
    var selectedJobTitle by remember { mutableStateOf(JobTitle.NOT_SELECTED) }
    var age by remember { mutableStateOf("") }
    var selectedGender by remember { mutableStateOf(Gender.NOT_SELECTED) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(scrollState)
            .padding(16.dp)
            .padding(top = 64.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Logo
        Image(
            painter = painterResource(R.drawable.person), // Replace with your logo resource
            contentDescription = "Person",
            modifier = Modifier.size(100.dp)
        )

        Spacer(modifier = Modifier.height(64.dp))

        // Name Field
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text(stringResource(R.string.name)) },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
            isError = !validationState.name.isValid,
            supportingText = {
                if (!validationState.name.isValid)
                    Text(
                        text = stringResource(validationState.name.errMessage),
                        color = MaterialTheme.colorScheme.error
                    )
            }
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Job Title Field
        DynamicSelectTextField(
            selectedValue = selectedJobTitle,
            options = JobTitle.entries.drop(1),
            label = stringResource(R.string.job_title),
            onValueChangedEvent = { selectedJobTitle = it },
            modifier = Modifier.fillMaxWidth(),
            isError = !validationState.jobTitle.isValid,
            supportingText = stringResource(validationState.jobTitle.errMessage)
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Age Field
        OutlinedTextField(
            value = age,
            onValueChange = { age = it },
            label = { Text(stringResource(R.string.age)) },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            isError = !validationState.age.isValid,
            supportingText = {
                if (!validationState.age.isValid)
                    Text(
                        text = stringResource(validationState.age.errMessage),
                        color = MaterialTheme.colorScheme.error
                    )
            }
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Gender Field
        DynamicSelectTextField(
            selectedValue = selectedGender,
            options = Gender.entries.drop(1),
            label = stringResource(R.string.gender),
            onValueChangedEvent = { selectedGender = it },
            modifier = Modifier.fillMaxWidth(),
            isError = !validationState.gender.isValid,
            supportingText = stringResource(validationState.gender.errMessage)
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Save Button
        ActionButton(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(R.string.save),
            isLoading = state.isLoading,
            onClick = {
                // Handle the save action here, or call onAction with the appropriate event
                onAction(
                    InfoScreenAction.OnSaveClick(
                        name = name,
                        jobTitle = selectedJobTitle,
                        age = age,
                        gender = selectedGender
                    )
                )
            }
        )
    }
}

@Preview
@Composable
private fun InfoScreenContentPreview() {
    InfoKeeperTheme {
        InfoScreenContent(
            state = InfoScreenState(),
            onAction = {},
            onUserSaved = {}
        )
    }
}
