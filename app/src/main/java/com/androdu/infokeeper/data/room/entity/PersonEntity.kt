package com.androdu.infokeeper.data.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.androdu.infokeeper.domain.utils.Gender
import com.androdu.infokeeper.domain.utils.JobTitle

/**
 * Entity representing a person in the database.
 *
 * @property id Unique identifier for the person. Auto-generated by Room.
 * @property name Name of the person.
 * @property age Age of the person.
 * @property jobTitle Job title of the person.
 * @property gender Gender of the person.
 */
@Entity(tableName = "person_table")
data class PersonEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val age: Int,
    val jobTitle: JobTitle,
    val gender: Gender
)