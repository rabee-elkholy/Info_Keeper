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
     * Deletes a person from the database.
     *
     * @param id PersonEntity id object to delete.
     */
    @Query("DELETE FROM person_table WHERE id = :id")
    suspend fun delete(id: Int)

    /**
     * Retrieves all persons from the database.
     *
     * @return List of all PersonEntity objects in the database sorted by id in descending order.
     */
    @Query("SELECT * FROM person_table ORDER BY id DESC")
    suspend fun getAllPersons(): List<PersonEntity>
}
