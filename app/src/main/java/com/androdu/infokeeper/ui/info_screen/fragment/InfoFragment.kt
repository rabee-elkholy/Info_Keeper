package com.androdu.infokeeper.ui.info_screen.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.androdu.infokeeper.R
import com.androdu.infokeeper.databinding.FragmentInfoBinding
import com.androdu.infokeeper.domain.utils.Gender
import com.androdu.infokeeper.domain.utils.JobTitle
import com.androdu.infokeeper.ui.info_screen.InfoScreenAction
import com.androdu.infokeeper.ui.info_screen.InfoScreenSideEffect
import com.androdu.infokeeper.ui.info_screen.InfoValidationState
import com.androdu.infokeeper.ui.info_screen.InfoViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * A fragment that displays a form for collecting user information including name, job title, age, and gender.
 * It interacts with [InfoViewModel] to handle user actions and observe changes in the UI state.
 */
class InfoFragment : Fragment(R.layout.fragment_info) {

    private var _binding: FragmentInfoBinding? = null
    private val binding get() = _binding!!

    private val viewModel: InfoViewModel by viewModel()

    /**
     * Inflates the layout for this fragment and returns the root view.
     *
     * @param inflater The LayoutInflater object that can be used to inflate any views in the fragment.
     * @param container The parent view that the fragment's UI should be attached to.
     * @param savedInstanceState If non-null, this fragment is being re-constructed from a previous saved state.
     * @return The root view of the fragment.
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    /**
     * Initializes the fragment's UI components, sets up listeners, and observes the ViewModel for state changes.
     *
     * @param view The view returned by [onCreateView].
     * @param savedInstanceState If non-null, this fragment is being re-constructed from a previous saved state.
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupAutocompleteAdapters()
        setupClickListeners()
        observeViewModel()
    }

    /**
     * Sets up autocomplete adapters for the job title and gender fields using predefined lists.
     */
    private fun setupAutocompleteAdapters() {
        val jobTitleList = JobTitle.entries
            .map { getString(it.getStringRes()) }
            .drop(1)
            .toTypedArray()

        val genderList = Gender.entries
            .map { getString(it.getStringRes()) }
            .drop(1)
            .toTypedArray()

        binding.jobTitleAutoComplete.setAdapter(
            ArrayAdapter(
                requireContext(),
                android.R.layout.simple_dropdown_item_1line,
                jobTitleList
            )
        )

        binding.genderAutoComplete.setAdapter(
            ArrayAdapter(
                requireContext(),
                android.R.layout.simple_dropdown_item_1line,
                genderList
            )
        )
    }

    /**
     * Sets up click listeners for the skip and save buttons.
     */
    private fun setupClickListeners() {
        binding.skipButton.setOnClickListener {
            findNavController().navigate(R.id.action_infoScreenFragment_to_infoListFragment)
        }

        binding.saveButton.setOnClickListener {
            viewModel.onAction(
                InfoScreenAction.OnSaveClick(
                    name = binding.nameEditText.text?.trim().toString(),
                    jobTitle = JobTitle.entries.find { getString(it.getStringRes()) == binding.jobTitleAutoComplete.text.trim().toString() }
                        ?: JobTitle.NOT_SELECTED,
                    age = binding.ageEditText.text?.trim().toString(),
                    gender = Gender.entries.find { getString(it.getStringRes()) == binding.genderAutoComplete.text.trim().toString() }
                        ?: Gender.NOT_SELECTED
                )
            )
        }
    }

    /**
     * Observes the ViewModel for state changes and side effects, updating the UI accordingly.
     */
    private fun observeViewModel() {
        lifecycleScope.launch {
            viewModel.state.collect { state ->
                handleLoadingState(state.isLoading)
                handleValidationState(state.infoValidationState)
                handleError(state.error)
            }
        }

        lifecycleScope.launch {
            viewModel.sideEffect.collect { sideEffect ->
                if (sideEffect is InfoScreenSideEffect.NavigateToListScreen && findNavController().currentDestination?.id != R.id.infoListFragment) {
                    clearFields()
                    findNavController().navigate(R.id.action_infoScreenFragment_to_infoListFragment)
                }
            }
        }
    }

    /**
     * Clears the input fields in the UI.
     */
    private fun clearFields() {
        binding.nameEditText.text?.clear()
        binding.ageEditText.text?.clear()
        binding.jobTitleAutoComplete.text?.clear()
        binding.genderAutoComplete.text?.clear()
    }

    /**
     * Updates the visibility of the progress bar based on the loading state.
     *
     * @param isLoading Whether or not the progress bar should be visible.
     */
    private fun handleLoadingState(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    /**
     * Updates the error messages on input fields based on validation state.
     *
     * @param validationState The current validation state of the form.
     */
    private fun handleValidationState(validationState: InfoValidationState) {
        binding.nameEditText.error = if (validationState.name.isValid) null else getString(validationState.name.errMessage)
        binding.ageEditText.error = if (validationState.age.isValid) null else getString(validationState.age.errMessage)
        binding.jobTitleAutoComplete.error = if (validationState.jobTitle.isValid) null else getString(validationState.jobTitle.errMessage)
        binding.genderAutoComplete.error = if (validationState.gender.isValid) null else getString(validationState.gender.errMessage)
    }

    /**
     * Displays an error message as a Toast if an error is present.
     *
     * @param error The resource ID of the error message to display.
     */
    private fun handleError(error: Int) {
        if (error != R.string.empty) {
            Toast.makeText(requireContext(), getString(error), Toast.LENGTH_SHORT).show()
        }
    }

    /**
     * Cleans up the binding reference when the view is destroyed.
     */
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

