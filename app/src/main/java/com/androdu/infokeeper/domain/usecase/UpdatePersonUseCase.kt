package com.androdu.infokeeper.domain.usecase

import com.androdu.infokeeper.data.repository.PersonRepository
import com.androdu.infokeeper.domain.model.Person
import com.androdu.infokeeper.domain.toPersonEntity

/**
 * Use case for updating a person in the repository.
 *
 * @property personRepository The repository used for updating the person.
 */
class UpdatePersonUseCase(private val personRepository: PersonRepository) {

    /**
     * Updates a person in the repository.
     *
     * @param person The person to update.
     * @return `true` if the update was successful, `false` otherwise.
     */
    suspend operator fun invoke(person: Person): Boolean {
        return try {
            personRepository.update(person.toPersonEntity())
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }
}
