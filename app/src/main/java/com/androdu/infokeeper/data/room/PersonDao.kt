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
     * @return `true` if the insert was successful, `false` otherwise.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(personEntity: PersonEntity): Boolean

    /**
     * Updates an existing person in the database.
     *
     * @param personEntity PersonEntity object to update.
     * @return `true` if the update was successful, `false` otherwise.
     */
    @Update
    suspend fun update(personEntity: PersonEntity): Boolean

    /**
     * Deletes a person from the database.
     *
     * @param personEntity PersonEntity object to delete.
     * @return `true` if the delete was successful, `false` otherwise.
     */
    @Delete
    suspend fun delete(personEntity: PersonEntity): Boolean

    /**
     * Retrieves all persons from the database.
     *
     * @return List of all PersonEntity objects in the database.
     */
    @Query("SELECT * FROM person_table")
    fun getAllPersons(): List<PersonEntity>
}
