package com.androdu.infokeeper.data.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.androdu.infokeeper.data.room.entity.Person

@Dao
interface PersonDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(person: Person)

    @Update
    suspend fun update(person: Person)

    @Delete
    suspend fun delete(person: Person)

    @Query("SELECT * FROM person_table")
    fun getAllPersons(): LiveData<List<Person>>
}
