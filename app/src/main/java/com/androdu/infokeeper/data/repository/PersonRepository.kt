package com.androdu.infokeeper.data.repository

import com.androdu.infokeeper.data.room.PersonDao
import com.androdu.infokeeper.data.room.entity.PersonEntity

/**
 * Interface for managing PersonEntity operations.
 */
interface PersonRepository {
    suspend fun insert(personEntity: PersonEntity)
    suspend fun update(personEntity: PersonEntity)
    suspend fun delete(personEntity: PersonEntity)
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
     */
    override suspend fun insert(personEntity: PersonEntity) {
        return personDao.insert(personEntity)
    }

    /**
     * Updates an existing person in the database.
     *
     * @param personEntity The person to update.
     */
    override suspend fun update(personEntity: PersonEntity) {
        return personDao.update(personEntity)
    }

    /**
     * Deletes a person from the database.
     *
     * @param personEntity The person to delete.
     */
    override suspend fun delete(personEntity: PersonEntity) {
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