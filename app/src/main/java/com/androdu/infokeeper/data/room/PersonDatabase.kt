package com.androdu.infokeeper.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.androdu.infokeeper.data.room.entity.Person

@Database(entities = [Person::class], version = 1)
abstract class PersonDatabase : RoomDatabase() {
    abstract fun personDao(): PersonDao
}
