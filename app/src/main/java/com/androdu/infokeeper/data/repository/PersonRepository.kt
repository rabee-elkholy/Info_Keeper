package com.androdu.infokeeper.data.repository

import com.androdu.infokeeper.data.room.PersonDao
import com.androdu.infokeeper.data.room.entity.PersonEntity

/**
 * Interface for managing PersonEntity operations.
 */
interface PersonRepository {
    suspend fun insert(personEntity: PersonEntity)
    suspend fun delete(id: Int)
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
     * Deletes a person from the database.
     *
     * @param id The person id to delete.
     */
    override suspend fun delete(id: Int) {
        return personDao.delete(id = id)
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