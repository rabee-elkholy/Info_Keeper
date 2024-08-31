package com.androdu.infokeeper.domain.usecase

import com.androdu.infokeeper.data.repository.PersonRepository
import com.androdu.infokeeper.domain.model.Person
import com.androdu.infokeeper.domain.toPersonEntity

/**
 * Use case for inserting a person into the repository.
 *
 * This class provides the logic for inserting a person into the `PersonRepository`.
 * It maps the domain model to a repository entity before performing the insertion.
 *
 * @property personRepository The repository used for inserting the person.
 */
class InsertPersonUseCase(private val personRepository: PersonRepository) {

    /**
     * Inserts a person into the repository.
     *
     * This function converts the provided `Person` domain model to a repository entity
     * using the `toPersonEntity()` extension function and then inserts it into the repository.
     * If the insertion is successful, it returns `true`. If an exception occurs during the
     * process, it logs the exception and returns `false`.
     *
     * @param person The person to insert into the repository. This should be a domain model
     *               instance that represents the person to be added.
     * @return `true` if the insertion was successful, `false` otherwise.
     *
     * @throws Exception If an error occurs during the insertion process.
     */
    suspend operator fun invoke(person: Person): Boolean {
        return try {
            personRepository.insert(person.toPersonEntity())
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }
}
