package com.androdu.infokeeper.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.androdu.infokeeper.data.room.entity.PersonEntity

/**
 * Database class for managing the PersonEntity database.
 *
 * @property personDao DAO for performing operations on PersonEntity.
 */
@Database(entities = [PersonEntity::class], version = 1)
abstract class PersonDatabase : RoomDatabase() {
    abstract fun personDao(): PersonDao
}
