package com.androdu.infokeeper.ui.info_list_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.androdu.infokeeper.domain.usecase.DeletePersonUseCase
import com.androdu.infokeeper.domain.usecase.GetAllPersonsUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * ViewModel for managing the state and side effects of the InfoListScreen.
 *
 * @property getAllPersonsUseCase Use case for retrieving all persons from the repository.
 * @property deletePersonUseCase Use case for deleting a person from the repository.
 */
class InfoListViewModel(
    private val getAllPersonsUseCase: GetAllPersonsUseCase,
    private val deletePersonUseCase: DeletePersonUseCase
) : ViewModel() {

    // StateFlow to observe the UI state.
    private val _state = MutableStateFlow(InfoListScreenState())
    val state = _state.asStateFlow()

    // StateFlow to observe the UI side effects.
    private val _sideEffect = MutableSharedFlow<InfoListScreenSideEffect>()
    val sideEffect = _sideEffect.asSharedFlow()

    init {
        getAllPersons()
    }

    /**
     * Handles actions received from the UI.
     *
     * @param action The action to be processed.
     */
    fun onAction(action: InfoListScreenAction) {
        when (action) {
            is InfoListScreenAction.OnDeleteClick -> handleDeleteClick(id = action.id)
        }
    }

    /**
     * Retrieves all persons and updates the state.
     */
    private fun getAllPersons() {
        viewModelScope.launch {
            val persons = getAllPersonsUseCase.invoke()
            _state.value = _state.value.copy(infoList = persons)
        }
    }

    /**
     * Handles the delete action for a person with the given ID.
     *
     * @param id The ID of the person to be deleted.
     */
    private fun handleDeleteClick(id: Int) {
        viewModelScope.launch {
            deletePersonUseCase.invoke(id)
            _sideEffect.emit(InfoListScreenSideEffect.PersonDeleteSuccess)
            getAllPersons()
        }
    }
}
