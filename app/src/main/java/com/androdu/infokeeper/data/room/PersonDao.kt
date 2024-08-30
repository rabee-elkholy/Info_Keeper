package com.androdu.infokeeper.data.room

import androidx.room.*
import com.androdu.infokeeper.data.room.entity.PersonEntity

/**
 * Data Access Object (DAO) for managing PersonEntity operations.
 */
@Dao
interface PersonDao {

    /**
     * Inserts a person into the database. If the person already exists, it replaces the existing record.
     *
     * @param personEntity PersonEntity object to insert.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(personEntity: PersonEntity)

    /**
     * Updates an existing person in the database.
     *
     * @param personEntity PersonEntity object to update.
     */
    @Update
    suspend fun update(personEntity: PersonEntity)

    /**
     * Deletes a person from the database.
     *
     * @param personEntity PersonEntity object to delete.
     */
    @Delete
    suspend fun delete(personEntity: PersonEntity)

    /**
     * Retrieves all persons from the database.
     *
     * @return List of all PersonEntity objects in the database.
     */
    @Query("SELECT * FROM person_table")
    fun getAllPersons(): List<PersonEntity>
}
