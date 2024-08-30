package com.androdu.infokeeper.domain.usecase

import com.androdu.infokeeper.data.repository.PersonRepository
import com.androdu.infokeeper.domain.model.Person
import com.androdu.infokeeper.domain.toPersonEntity

/**
 * Use case for inserting a person into the repository.
 *
 * @property personRepository The repository used for inserting the person.
 */
class InsertPersonUseCase(private val personRepository: PersonRepository) {

    /**
     * Inserts a person into the repository.
     *
     * @param person The person to insert.
     * @return `true` if the insert was successful, `false` otherwise.
     */
    suspend operator fun invoke(person: Person): Boolean {
        return personRepository.insert(person.toPersonEntity())
    }
}
