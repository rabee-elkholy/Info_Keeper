package com.androdu.infokeeper.domain.utils

import androidx.annotation.StringRes
import com.androdu.infokeeper.R

/**
 * Provides validation functions for person information inputs.
 *
 * This object contains methods to validate different types of input data for a person,
 * such as name, age, job title, and gender. Each method returns a `ValidationResult`
 * indicating whether the input is valid and providing an error message resource ID if invalid.
 */
object PersonInfoValidator {
    private const val MAX_NAME_LENGTH = 20

    /**
     * Validates the name input.
     *
     * @param name The entered name.
     * @return The validation result containing an error message resource ID if invalid.
     *         - `R.string.name_cannot_be_empty` if the name is blank.
     *         - `R.string.name_too_long` if the name exceeds the maximum allowed length.
     *         - Valid if none of the above conditions are met.
     */
    fun validateName(name: String): ValidationResult = when {
        name.isBlank() -> ValidationResult(errMessage = R.string.name_cannot_be_empty)
        name.length > MAX_NAME_LENGTH -> ValidationResult(errMessage = R.string.name_too_long)
        else -> ValidationResult(isValid = true)
    }

    /**
     * Validates the age input.
     *
     * @param age The entered age.
     * @return The validation result containing an error message resource ID if invalid.
     *         - `R.string.age_cannot_be_empty` if the age is not a number.
     *         - `R.string.age_cannot_be_zero` if the age is zero.
     *         - `R.string.age_too_high` if the age is greater than 100.
     *         - Valid if none of the above conditions are met.
     */
    fun validateAge(age: String): ValidationResult = when {
        age.toIntOrNull() == null -> ValidationResult(errMessage = R.string.age_cannot_be_empty)
        age.toInt() == 0 -> ValidationResult(errMessage = R.string.age_cannot_be_zero)
        age.toInt() > 100 -> ValidationResult(errMessage = R.string.age_too_high)
        else -> ValidationResult(isValid = true)
    }

    /**
     * Validates the job title selection.
     *
     * @param jobTitle The selected job title.
     * @return The validation result containing an error message resource ID if invalid.
     *         - `R.string.invalid_job_title` if no valid job title is selected.
     *         - Valid if the job title is not `JobTitle.NOT_SELECTED`.
     */
    fun validateJobTitle(jobTitle: JobTitle): ValidationResult = when {
        jobTitle == JobTitle.NOT_SELECTED -> ValidationResult(errMessage = R.string.invalid_job_title)
        else -> ValidationResult(isValid = true)
    }

    /**
     * Validates the gender selection.
     *
     * @param gender The selected gender.
     * @return The validation result containing an error message resource ID if invalid.
     *         - `R.string.invalid_gender` if no valid gender is selected.
     *         - Valid if the gender is not `Gender.NOT_SELECTED`.
     */
    fun validateGender(gender: Gender): ValidationResult = when {
        gender == Gender.NOT_SELECTED -> ValidationResult(errMessage = R.string.invalid_gender)
        else -> ValidationResult(isValid = true)
    }
}

/**
 * Represents the result of a validation check.
 *
 * @property isValid Indicates whether the input is valid.
 * @property errMessage The error message resource ID if the input is invalid. Defaults to `R.string.empty`.
 */
data class ValidationResult(
    val isValid: Boolean = false,
    @StringRes val errMessage: Int = R.string.empty
)
