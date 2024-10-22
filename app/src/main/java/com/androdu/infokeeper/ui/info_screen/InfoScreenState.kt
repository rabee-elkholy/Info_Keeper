package com.androdu.infokeeper.ui.info_screen

import androidx.annotation.StringRes
import com.androdu.infokeeper.R
import com.androdu.infokeeper.domain.utils.ValidationResult
import com.androdu.infokeeper.domain.utils.Gender
import com.androdu.infokeeper.domain.utils.JobTitle

/**
 * Represents the state of the Info Screen UI.
 *
 * @param name The entered name.
 * @param age The entered age.
 * @param jobTitle The selected job title.
 * @param gender The selected gender.
 * @param infoValidationState The validation state of the form inputs.
 * @param isLoading Indicates whether the screen is currently loading.
 * @param error Optional error message resource ID to be displayed.
 */
data class InfoScreenState(
    val name: String = "",
    val age: String = "",
    val jobTitle: JobTitle = JobTitle.NOT_SELECTED,
    val gender: Gender = Gender.NOT_SELECTED,
    val infoValidationState: InfoValidationState = InfoValidationState(),
    val isLoading: Boolean = false,
    @StringRes val error: Int = R.string.empty
) {
    /**
     * Compares this InfoScreenState with another object for equality.
     *
     * @param other The other object to compare with.
     * @return False always, as equality check is disabled for this class.
     */
    override fun equals(other: Any?): Boolean {
        return if (this === other) false // Always return false for the same instance
        else if (other == null || javaClass != other.javaClass) false
        else false
    }
}

/**
 * Represents the validation state of the form inputs on the Info Screen.
 *
 * @param name Validation result for the name input.
 * @param age Validation result for the age input.
 * @param jobTitle Validation result for the job title selection.
 * @param gender Validation result for the gender selection.
 */
data class InfoValidationState(
    val name: ValidationResult = ValidationResult(isValid = true),
    val age: ValidationResult = ValidationResult(isValid = true),
    val jobTitle: ValidationResult = ValidationResult(isValid = true),
    val gender: ValidationResult = ValidationResult(isValid = true)
) {
    /**
     * Checks if all form inputs are valid based on their validation results.
     *
     * @return True if all inputs are valid, false otherwise.
     */
    fun isValidInfo(): Boolean = name.isValid && age.isValid && jobTitle.isValid && gender.isValid
}

/**
 * Represents the side effects of actions performed in the Info Screen.
 */
sealed interface InfoScreenSideEffect {
    /**
     * Represents the side effect of navigating to the list screen.
     */
    data object NavigateToListScreen : InfoScreenSideEffect
}
