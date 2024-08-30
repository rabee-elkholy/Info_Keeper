package com.androdu.infokeeper.domain.utils

import androidx.annotation.StringRes
import com.androdu.infokeeper.R

/**
 * Validates the person information inputs.
 */
object PersonInfoValidator {
    private const val MAX_NAME_LENGTH = 20

    /**
     * Validates the name input.
     * @param name The entered name.
     * @return The validation result with an error message if invalid.
     */
    fun validateName(name: String): ValidationResult = when {
        name.isBlank() -> ValidationResult(errMessage = R.string.name_cannot_be_empty)
        name.length > MAX_NAME_LENGTH -> ValidationResult(errMessage = R.string.name_too_long)
        else -> ValidationResult(isValid = true)
    }

    /**
     * Validates the age input.
     * @param age The entered age.
     * @return The validation result with an error message if invalid.
     */
    fun validateAge(age: Int): ValidationResult = when {
        age == 0 -> ValidationResult(errMessage = R.string.age_cannot_be_zero)
        else -> ValidationResult(isValid = true)
    }

    /**
     * Validates the job title selection.
     * @param jobTitle The selected job title.
     * @return The validation result with an error message if invalid.
     */
    fun validateJobTitle(jobTitle: JobTitle): ValidationResult = when {
        jobTitle == JobTitle.NOT_SELECTED -> ValidationResult(errMessage = R.string.job_title_cannot_be_empty)
        else -> ValidationResult(isValid = true)
    }

    /**
     * Validates the gender selection.
     * @param gender The selected gender.
     * @return The validation result with an error message if invalid.
     */
    fun validateGender(gender: Gender): ValidationResult = when {
        gender == Gender.NOT_SELECTED -> ValidationResult(errMessage = R.string.gender_cannot_be_empty)
        else -> ValidationResult(isValid = true)
    }
}

/**
 * Represents the result of a validation.
 * @param isValid Indicates whether the input is valid.
 * @param errMessage The error message resource ID if the input is invalid.
 */
data class ValidationResult(
    val isValid: Boolean = false,
    @StringRes val errMessage: Int = R.string.empty
)
