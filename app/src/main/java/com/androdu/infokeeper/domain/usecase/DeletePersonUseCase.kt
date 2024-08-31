package com.androdu.infokeeper.domain.usecase

import com.androdu.infokeeper.data.repository.PersonRepository

/**
 * Use case for deleting a person from the repository.
 *
 * This class encapsulates the logic required to delete a person from the data repository.
 * It interacts with the `PersonRepository` to perform the deletion operation.
 *
 * @property personRepository The repository used for deleting the person.
 */
class DeletePersonUseCase(private val personRepository: PersonRepository) {

    /**
     * Deletes a person from the repository.
     *
     * This function invokes the delete operation on the `personRepository` using the provided person ID.
     * If the deletion is successful, it returns `true`. If an exception occurs during the operation,
     * it logs the exception and returns `false`.
     *
     * @param id The ID of the person to be deleted.
     * @return `true` if the deletion was successful, `false` otherwise.
     *
     * @throws Exception If an error occurs during the deletion process.
     */
    suspend operator fun invoke(id: Int): Boolean {
        return try {
            personRepository.delete(id)
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }
}
