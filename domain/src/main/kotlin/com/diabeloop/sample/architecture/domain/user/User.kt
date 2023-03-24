package com.diabeloop.sample.architecture.domain.user

/**
 * Data class representing user.
 *
 * @property id identifier in database.
 * @property firstName
 * @property lastName
 * @property type
 */
data class User(
    val id: Int? = null,
    val firstName: String,
    val lastName: String,
    val type: UserType,
)
