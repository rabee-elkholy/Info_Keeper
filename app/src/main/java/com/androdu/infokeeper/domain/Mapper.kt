package com.androdu.infokeeper.domain

import com.androdu.infokeeper.data.room.entity.PersonEntity
import com.androdu.infokeeper.domain.model.Person

/**
 * Converts a [Person] ui model to a [PersonEntity] for database operations.
 *
 * @return The corresponding [PersonEntity] instance.
 */
fun Person.toPersonEntity(): PersonEntity = PersonEntity(
    id = id,
    name = name,
    age = age,
    jobTitle = jobTitle,
    gender = gender
)

/**
 * Converts a [PersonEntity] from the database to a [Person] ui model.
 *
 * @return The corresponding [Person] instance.
 */
fun PersonEntity.toPerson(): Person = Person(
    id = id,
    name = name,
    age = age,
    jobTitle = jobTitle,
    gender = gender
)
