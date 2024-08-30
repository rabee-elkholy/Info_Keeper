package com.androdu.infokeeper.data.repository

import com.androdu.infokeeper.data.room.PersonDao
import com.androdu.infokeeper.data.room.entity.PersonEntity

/**
 * Interface for managing PersonEntity operations.
 */
interface PersonRepository {
    suspend fun insert(personEntity: PersonEntity): Boolean
    suspend fun update(personEntity: PersonEntity): Boolean
    suspend fun delete(personEntity: PersonEntity): Boolean
    suspend fun getAllPersons(): List<PersonEntity>
}

/**
 * Implementation of [PersonRepository] that uses [PersonDao] for database operations.
 *
 * @property personDao The DAO used for interacting with the database.
 */
class PersonRepositoryImpl(
    private val personDao: PersonDao
) : PersonRepository {

    /**
     * Inserts a person into the database.
     *
     * @param personEntity The person to insert.
     * @return `true` if the insert was successful, `false` otherwise.
     */
    override suspend fun insert(personEntity: PersonEntity): Boolean {
        return personDao.insert(personEntity)
    }

    /**
     * Updates an existing person in the database.
     *
     * @param personEntity The person to update.
     * @return `true` if the update was successful, `false` otherwise.
     */
    override suspend fun update(personEntity: PersonEntity): Boolean {
        return personDao.update(personEntity)
    }

    /**
     * Deletes a person from the database.
     *
     * @param personEntity The person to delete.
     * @return `true` if the delete was successful, `false` otherwise.
     */
    override suspend fun delete(personEntity: PersonEntity): Boolean {
        return personDao.delete(personEntity)
    }

    /**
     * Retrieves all persons from the database.
     *
     * @return A list of all PersonEntity objects in the database.
     */
    override suspend fun getAllPersons(): List<PersonEntity> {
        return personDao.getAllPersons()
    }
}