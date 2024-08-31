package com.androdu.infokeeper.ui.info_list_screen

import androidx.annotation.StringRes
import com.androdu.infokeeper.R
import com.androdu.infokeeper.domain.model.Person

/**
 * Represents the state of the InfoListScreen.
 *
 * @property infoList A list of [Person] objects to be displayed on the screen.
 * @property error A string resource ID for displaying error messages. Defaults to [R.string.empty].
 */
data class InfoListScreenState(
    val infoList: List<Person> = emptyList(),
    @StringRes val error: Int = R.string.empty
)

/**
 * Represents the side effects of actions performed on the InfoListScreen.
 */
sealed interface InfoListScreenSideEffect {

    /**
     * Indicates that a person was successfully deleted.
     */
    data object PersonDeleteSuccess : InfoListScreenSideEffect
}
