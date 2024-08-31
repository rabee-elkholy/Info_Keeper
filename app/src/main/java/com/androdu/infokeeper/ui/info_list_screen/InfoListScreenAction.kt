package com.androdu.infokeeper.ui.info_list_screen

/**
 * Represents the different actions that can be performed on the InfoListScreen.
 */
sealed interface InfoListScreenAction {

    /**
     * Action triggered when a delete button is clicked for a person.
     *
     * @property id The ID of the person to be deleted.
     */
    data class OnDeleteClick(val id: Int) : InfoListScreenAction
}
