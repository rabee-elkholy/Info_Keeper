package com.androdu.infokeeper.domain.model

import androidx.annotation.DrawableRes
import com.androdu.infokeeper.R
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
 * @property avatar Avatar of the person.
 */
data class Person(
    val id: Int = 0,
    val name: String,
    val age: Int,
    val jobTitle: JobTitle,
    val gender: Gender,
    @DrawableRes val avatar: Int = getRandomImage(gender)
)

/**
 * Returns a random image resource ID based on the provided gender.
 *
 * This function selects a random drawable resource ID from a predefined list of images
 * depending on the specified `gender`. If the gender is `Gender.MALE`, it returns a random
 * image from the list of male images. If the gender is `Gender.FEMALE`, it returns a random
 * image from the list of female images. For any other gender or if the gender is unspecified,
 * it returns a default image resource ID.
 *
 * @param gender The gender for which to select an image. It should be of type [Gender].
 *               - [Gender.MALE]: To select from the male images list.
 *               - [Gender.FEMALE]: To select from the female images list.
 *               - Any other value: Returns the default image resource ID.
 *
 * @return An integer representing the drawable resource ID of the selected image.
 *         - For `Gender.MALE`: Returns a random image from `maleImages`.
 *         - For `Gender.FEMALE`: Returns a random image from `femaleImages`.
 *         - For any other gender or if the gender is not specified: Returns `R.drawable.person`.
 */
fun getRandomImage(gender: Gender): Int {
    val maleImages = listOf(R.drawable.ic_man_1, R.drawable.ic_man_2, R.drawable.ic_man_3)
    val femaleImages = listOf(R.drawable.ic_woman_1, R.drawable.ic_woman_2, R.drawable.ic_woman_3)
    return when (gender) {
        Gender.MALE -> maleImages.random()
        Gender.FEMALE -> femaleImages.random()
        else -> R.drawable.person
    }
}
