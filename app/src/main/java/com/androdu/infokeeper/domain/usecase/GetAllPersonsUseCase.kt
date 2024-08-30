package com.androdu.infokeeper.domain.usecase

import com.androdu.infokeeper.data.repository.PersonRepository
import com.androdu.infokeeper.domain.model.Person
import com.androdu.infokeeper.domain.toPerson

/**
 * Use case for retrieving all persons from the repository.
 *
 * @property personRepository The repository used for retrieving persons.
 */
class GetAllPersonsUseCase(private val personRepository: PersonRepository) {

    /**
     * Retrieves all persons from the repository and maps them to domain models.
     *
     * @return A list of all persons in the domain model format.
     */
    suspend operator fun invoke(): List<Person> {
        return personRepository.getAllPersons().map { it.toPerson() }
    }
}
