package com.androdu.infokeeper.ui.info_list_screen.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.androdu.infokeeper.R
import com.androdu.infokeeper.databinding.FragmentInfoListBinding
import com.androdu.infokeeper.ui.info_list_screen.InfoListViewModel
import com.androdu.infokeeper.ui.info_list_screen.InfoListScreenAction
import com.androdu.infokeeper.ui.info_list_screen.InfoListScreenState
import com.androdu.infokeeper.ui.info_list_screen.InfoListScreenSideEffect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Fragment for displaying a list of persons.
 *
 * This fragment initializes the UI components, sets up the RecyclerView, and observes changes
 * in the `InfoListViewModel` to update the UI accordingly. It also handles user interactions
 * such as back navigation and item deletions.
 */
class InfoListFragment : Fragment() {

    private var _binding: FragmentInfoListBinding? = null
    private val binding get() = _binding!!

    private val viewModel: InfoListViewModel by viewModel()
    private lateinit var adapter: PersonAdapter

    /**
     * Inflates the layout for this fragment and initializes binding.
     *
     * @param inflater LayoutInflater to inflate the layout.
     * @param container The container view that will hold the fragment.
     * @param savedInstanceState A Bundle containing saved instance state.
     * @return The root view of the fragment's layout.
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentInfoListBinding.inflate(inflater, container, false)
        return binding.root
    }

    /**
     * Sets up UI components and observes changes in the view model.
     *
     * @param view The root view of the fragment.
     * @param savedInstanceState A Bundle containing saved instance state.
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupClicks()
        setupRecyclerView()
        observeViewModel()
    }

    /**
     * Sets up click listeners for UI components.
     */
    private fun setupClicks() {
        binding.backButton.setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }
    }

    /**
     * Initializes and sets up the RecyclerView with an adapter.
     */
    private fun setupRecyclerView() {
        adapter = PersonAdapter(
            context = requireContext(),
            persons = mutableListOf(),
            onDeleteClick = {
                viewModel.onAction(InfoListScreenAction.OnDeleteClick(it.id))
            }
        )

        binding.personList.layoutManager = LinearLayoutManager(requireContext())
        binding.personList.adapter = adapter
    }

    /**
     * Observes the view model for state and side effects, updating the UI accordingly.
     */
    private fun observeViewModel() {
        lifecycleScope.launch {
            viewModel.state.collect { state ->
                updateUi(state)
            }
        }

        lifecycleScope.launch {
            viewModel.sideEffect.collect { sideEffect ->
                when (sideEffect) {
                    is InfoListScreenSideEffect.PersonDeleteSuccess -> {
                        Toast.makeText(requireContext(), getString(R.string.person_deleted), Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    /**
     * Updates the UI based on the current state.
     *
     * @param state The current state of the Info List screen.
     */
    private fun updateUi(state: InfoListScreenState) {
        adapter.setItems(state.infoList)
        if (state.infoList.isEmpty()){
            binding.personList.visibility = View.GONE
            binding.emptyListText.visibility = View.VISIBLE
        } else {
            binding.personList.visibility = View.VISIBLE
            binding.emptyListText.visibility = View.GONE
        }
    }

    /**
     * Cleans up the binding when the view is destroyed.
     */
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
