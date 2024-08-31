package com.androdu.infokeeper.ui_compose.info_screen

import androidx.annotation.StringRes
import com.androdu.infokeeper.R
import com.androdu.infokeeper.domain.utils.ValidationResult
import com.androdu.infokeeper.domain.utils.Gender
import com.androdu.infokeeper.domain.utils.JobTitle

/**
 * Represents the state of the Info Screen UI.
 * @param name The entered name.
 * @param age The entered age.
 * @param jobTitle The selected job title.
 * @param gender The selected gender.
 * @param infoValidationState The validation state of the form inputs.
 * @param isLoading Indicates whether the screen is currently loading.
 * @param infoSaved Indicates whether the info has been successfully saved.
 */
data class InfoScreenState(
    val name: String = "",
    val age: String = "",
    val jobTitle: JobTitle = JobTitle.NOT_SELECTED,
    val gender: Gender = Gender.NOT_SELECTED,
    val infoValidationState: InfoValidationState = InfoValidationState(),
    val isLoading: Boolean = false,
    val infoSaved: Boolean = false,
    @StringRes val error: Int = R.string.empty
)

/**
 * Represents the validation state of the form inputs on the Info Screen.
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
     * Checks if all inputs are valid.
     * @return True if all inputs are valid, false otherwise.
     */
    fun isValidInfo(): Boolean = name.isValid && age.isValid && jobTitle.isValid && gender.isValid
}
