package com.androdu.infokeeper.ui.info_list_screen.fragment

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.androdu.infokeeper.R
import com.androdu.infokeeper.databinding.PersonListItemBinding
import com.androdu.infokeeper.domain.model.Person

/**
 * Adapter for displaying a list of persons in a RecyclerView.
 *
 * @property context The context used for accessing resources.
 * @property persons The list of persons to be displayed.
 * @property onDeleteClick Callback invoked when a delete action is triggered.
 */
class PersonAdapter(
    private val context: Context,
    private val persons: MutableList<Person>,
    private val onDeleteClick: (Person) -> Unit
) : RecyclerView.Adapter<PersonAdapter.PersonViewHolder>() {

    /**
     * Creates a new ViewHolder for a person item.
     *
     * @param parent The parent view group that the ViewHolder's view will be attached to.
     * @param viewType The view type of the new ViewHolder.
     * @return A new instance of PersonViewHolder.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder {
        val binding = PersonListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PersonViewHolder(context, binding, onDeleteClick)
    }

    /**
     * Binds a person item to the ViewHolder.
     *
     * @param holder The ViewHolder to bind data to.
     * @param position The position of the item within the adapter's data set.
     */
    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        val person = persons[position]
        holder.bind(person)
    }

    /**
     * Returns the total number of items in the adapter.
     *
     * @return The number of items in the list.
     */
    override fun getItemCount(): Int = persons.size

    /**
     * Updates the list of persons displayed by the adapter.
     *
     * @param newPersons The new list of persons.
     */
    fun setItems(newPersons: List<Person>) {
        persons.clear()
        persons.addAll(newPersons)
        notifyDataSetChanged()
    }

    /**
     * ViewHolder for displaying a person item.
     *
     * @property context The context used for accessing resources.
     * @property binding The binding for the person list item layout.
     * @property onDeleteClick Callback invoked when a delete action is triggered.
     */
    class PersonViewHolder(
        private val context: Context,
        private val binding: PersonListItemBinding,
        private val onDeleteClick: (Person) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        /**
         * Binds a person object to the ViewHolder's views.
         *
         * @param person The person object to bind.
         */
        fun bind(person: Person) {
            binding.profileImage.setImageResource(person.avatar)
            binding.nameTextView.text = person.name
            binding.titleTextView.text = context.getString(person.jobTitle.getStringRes())
            binding.detailsTextView.text =
                context.getString(R.string.age_gender, person.age.toString(), context.getString(person.gender.getStringRes()))

            binding.deleteButton.setOnClickListener {
                onDeleteClick(person)
            }
        }
    }
}
