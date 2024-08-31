package com.androdu.infokeeper.ui.info_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.androdu.infokeeper.R
import com.androdu.infokeeper.domain.utils.PersonInfoValidator
import com.androdu.infokeeper.domain.model.Person
import com.androdu.infokeeper.domain.usecase.InsertPersonUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * ViewModel for managing the state and handling user actions on the Info Screen.
 *
 * @param insertPersonUseCase Use case for inserting a person into the repository.
 * @param personInfoValidator Validator for the person information.
 */
class InfoViewModel(
    private val insertPersonUseCase: InsertPersonUseCase,
    private val personInfoValidator: PersonInfoValidator = PersonInfoValidator
) : ViewModel() {

    // StateFlow to observe the current state of the Info Screen.
    private val _state = MutableStateFlow(InfoScreenState())
    val state = _state.asStateFlow()

    // SharedFlow for emitting side effects such as navigation events.
    private val _sideEffect = MutableSharedFlow<InfoScreenSideEffect>()
    val sideEffect = _sideEffect.asSharedFlow()

    /**
     * Handles actions from the UI.
     *
     * @param action The action to be handled.
     */
    fun onAction(action: InfoScreenAction) {
        when (action) {
            is InfoScreenAction.OnSaveClick -> handleSaveClick(action)
        }
    }

    /**
     * Handles the save button click event by validating the user inputs and saving the person information.
     *
     * @param action Contains the user-entered data.
     */
    private fun handleSaveClick(action: InfoScreenAction.OnSaveClick) {
        // Update the state with the latest user input.
        _state.value = _state.value.copy(
            name = action.name,
            age = action.age,
            jobTitle = action.jobTitle,
            gender = action.gender
        )

        // Validate the inputs and update the validation state.
        val validationState = validateInfo()
        _state.value = _state.value.copy(infoValidationState = validationState)

        // Save the person information if all validations are successful.
        if (validationState.isValidInfo()) savePerson()
    }

    /**
     * Validates the user inputs.
     *
     * @return The validation state of the inputs.
     */
    private fun validateInfo(): InfoValidationState {
        return InfoValidationState(
            name = personInfoValidator.validateName(_state.value.name),
            age = personInfoValidator.validateAge(_state.value.age),
            jobTitle = personInfoValidator.validateJobTitle(_state.value.jobTitle),
            gender = personInfoValidator.validateGender(_state.value.gender)
        )
    }

    /**
     * Saves the person information if all validations pass.
     *
     * This method simulates a network or database operation with a delay, then updates the state
     * to reflect the result of the save operation and emits a side effect to navigate to the list screen.
     */
    private fun savePerson() {
        val person = Person(
            name = _state.value.name,
            age = _state.value.age.toInt(),
            jobTitle = _state.value.jobTitle,
            gender = _state.value.gender
        )

        // Launches a coroutine to save the person asynchronously.
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true)
            delay(1000) // Simulate a delay for the save operation

            // Perform the save operation using the use case.
            val infoSaved = insertPersonUseCase.invoke(person)
            _state.value = _state.value.copy(
                isLoading = false,
                error = if (infoSaved) R.string.empty else R.string.general_error
            )

            // Emit side effect to navigate to the list screen.
            _sideEffect.emit(InfoScreenSideEffect.NavigateToListScreen)
        }
    }
}
