package com.androdu.infokeeper.domain.model

import com.androdu.infokeeper.domain.utils.Gender
import com.androdu.infokeeper.domain.utils.JobTitle

/**
 * Model class representing a person in the ui layer.
 *
 * @property id Unique identifier for the person.
 * @property name Name of the person.
 * @property age Age of the person.
 * @property jobTitle Job title of the person.
 * @property gender Gender of the person.
 */
data class Person(
    val id: Int = 0,
    val name: String,
    val age: Int,
    val jobTitle: JobTitle,
    val gender: Gender
)
