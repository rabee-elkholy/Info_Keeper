package com.androdu.infokeeper.ui_compose.info_screen

import com.androdu.infokeeper.domain.utils.Gender
import com.androdu.infokeeper.domain.utils.JobTitle

/**
 * Represents all possible actions that can be taken on the Info Screen.
 */
sealed interface InfoScreenAction {
    /**
     * Triggered when the save button is clicked.
     * @param name The entered name.
     * @param age The entered age.
     * @param jobTitle The selected job title.
     * @param gender The selected gender.
     */
    data class OnSaveClick(val name: String, val jobTitle: JobTitle, val age: String, val gender: Gender) : InfoScreenAction
}
