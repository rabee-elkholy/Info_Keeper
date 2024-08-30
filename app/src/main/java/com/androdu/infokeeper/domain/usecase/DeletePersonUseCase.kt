package com.androdu.infokeeper.domain.usecase

import com.androdu.infokeeper.data.repository.PersonRepository
import com.androdu.infokeeper.domain.model.Person
import com.androdu.infokeeper.domain.toPersonEntity

/**
 * Use case for deleting a person from the repository.
 *
 * @property personRepository The repository used for deleting the person.
 */
class DeletePersonUseCase(private val personRepository: PersonRepository) {

    /**
     * Deletes a person from the repository.
     *
     * @param person The person to delete.
     */
    suspend operator fun invoke(person: Person): Boolean {
        return try {
            personRepository.delete(person.toPersonEntity())
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }
}
