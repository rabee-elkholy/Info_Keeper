package com.androdu.infokeeper

import com.androdu.infokeeper.data.room.entity.PersonEntity
import com.androdu.infokeeper.domain.model.Person
import com.androdu.infokeeper.domain.toPerson
import com.androdu.infokeeper.domain.toPersonEntity
import com.androdu.infokeeper.domain.utils.Gender
import com.androdu.infokeeper.domain.utils.JobTitle
import org.junit.Test
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull

/**
 * Unit tests for the extension functions converting between [Person] and [PersonEntity].
 */
class ConversionTests {

    /**
     * Tests that the [Person.toPersonEntity] extension function correctly converts a [Person] instance
     * to a [PersonEntity] instance.
     */
    @Test
    fun `toPersonEntity converts Person to PersonEntity correctly`() {
        // Given
        val person = Person(
            id = 1,
            name = "John Doe",
            age = 30,
            jobTitle = JobTitle.ANDROID_DEVELOPER,
            gender = Gender.MALE,
            avatar = R.drawable.ic_man_1
        )
        
        // When
        val personEntity = person.toPersonEntity()
        
        // Then
        assertEquals(person.id, personEntity.id)
        assertEquals(person.name, personEntity.name)
        assertEquals(person.age, personEntity.age)
        assertEquals(person.jobTitle, personEntity.jobTitle)
        assertEquals(person.gender, personEntity.gender)
        assertEquals(person.avatar, personEntity.avatar)
    }

    /**
     * Tests that the [PersonEntity.toPerson] extension function correctly converts a [PersonEntity] instance
     * to a [Person] instance.
     */
    @Test
    fun `toPerson converts PersonEntity to Person correctly`() {
        // Given
        val personEntity = PersonEntity(
            id = 1,
            name = "Jane Doe",
            age = 25,
            jobTitle = JobTitle.PRODUCT_MANAGER,
            gender = Gender.FEMALE,
            avatar = R.drawable.ic_man_1
        )
        
        // When
        val person = personEntity.toPerson()
        
        // Then
        assertEquals(personEntity.id, person.id)
        assertEquals(personEntity.name, person.name)
        assertEquals(personEntity.age, person.age)
        assertEquals(personEntity.jobTitle, person.jobTitle)
        assertEquals(personEntity.gender, person.gender)
        assertEquals(personEntity.avatar, person.avatar)
    }

    /**
     * Tests that the conversion functions handle default or empty values correctly.
     */
    @Test
    fun `conversion functions handle default values correctly`() {
        // Given
        val person = Person(
            id = 0,
            name = "",
            age = 0,
            jobTitle = JobTitle.NOT_SELECTED,
            gender = Gender.NOT_SELECTED,
            avatar = 0
        )
        
        // When
        val personEntity = person.toPersonEntity()
        val convertedPerson = personEntity.toPerson()
        
        // Then
        assertEquals(person.id, personEntity.id)
        assertEquals(person.name, personEntity.name)
        assertEquals(person.age, personEntity.age)
        assertEquals(person.jobTitle, personEntity.jobTitle)
        assertEquals(person.gender, personEntity.gender)
        assertEquals(person.avatar, personEntity.avatar)
        assertEquals(person, convertedPerson)
    }

    /**
     * Tests that the conversion functions are symmetric, meaning converting back and forth yields the original object.
     */
    @Test
    fun `conversion functions are symmetric`() {
        // Given
        val originalPerson = Person(
            id = 4,
            name = "Charlie Green",
            age = 35,
            jobTitle = JobTitle.SCRUM_MASTER,
            gender = Gender.FEMALE,
            avatar = R.drawable.ic_woman_1
        )
        
        // When
        val personEntity = originalPerson.toPersonEntity()
        val convertedPerson = personEntity.toPerson()
        
        // Then
        assertEquals(originalPerson, convertedPerson)
    }
}
