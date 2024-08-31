package com.androdu.infokeeper.ui_compose.info_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.androdu.infokeeper.R
import com.androdu.infokeeper.domain.utils.PersonInfoValidator
import com.androdu.infokeeper.domain.model.Person
import com.androdu.infokeeper.domain.usecase.InsertPersonUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * ViewModel for the Info Screen, managing state and handling user actions.
 * @param insertPersonUseCase The use case for inserting a person into the repository.
 * @param personInfoValidator Validator for the person information.
 */
class InfoViewModel(
    private val insertPersonUseCase: InsertPersonUseCase,
    private val personInfoValidator: PersonInfoValidator = PersonInfoValidator
) : ViewModel() {

    // StateFlow to observe the UI state.
    private val _state = MutableStateFlow(InfoScreenState())
    val state = _state.asStateFlow()

    /**
     * Handles actions from the UI.
     * @param action The action to be handled.
     */
    fun onAction(action: InfoScreenAction) {
        when (action) {
            is InfoScreenAction.OnSaveClick -> handleSaveClick(action)
        }
    }

    /**
     * Handles the save button click.
     * @param action Contains the user-entered data.
     */
    private fun handleSaveClick(action: InfoScreenAction.OnSaveClick) {
        _state.value = _state.value.copy(
            name = action.name,
            age = action.age,
            jobTitle = action.jobTitle,
            gender = action.gender
        )

        val validationState = validateInfo()
        _state.value = _state.value.copy(infoValidationState = validationState)

        if (validationState.isValidInfo()) savePerson()
    }

    /**
     * Validates the user inputs.
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
            delay(2000)

            val infoSaved = insertPersonUseCase.invoke(person)
            _state.value = _state.value.copy(
                isLoading = false,
                infoSaved = infoSaved,
                error = if (infoSaved) R.string.empty else R.string.general_error
            )
        }
    }
}
