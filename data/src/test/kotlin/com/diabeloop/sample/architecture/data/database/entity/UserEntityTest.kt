package com.diabeloop.sample.architecture.data.database.entity

import com.diabeloop.sample.architecture.data.database.Database
import com.diabeloop.sample.architecture.domain.user.User
import com.diabeloop.sample.architecture.domain.user.UserType
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

/**
 * Test class for [UserEntity].
 *
 */
internal class UserEntityTest {

    @Test
    fun user_withId_toUserEntity_shall_returnUserEntity() {
        // Arrange
        val user = User(
            id = 1,
            firstName = "John",
            lastName = "Wick",
            type = UserType.TYPE_1
        )

        // Act
        val result = user.toUserEntity()

        // Assert
        assertEquals(user.id, result.id)
        assertEquals(user.firstName, result.firstName)
        assertEquals(user.lastName, result.lastName)
        assertEquals(user.type, result.type)
    }

    @Test
    fun user_withoutId_toUserEntity_shall_returnUserEntity() {
        // Arrange
        val user = User(
            firstName = "John",
            lastName = "Wick",
            type = UserType.TYPE_1
        )

        // Act
        val result = user.toUserEntity()

        // Assert
        assertEquals(Database.DEFAULT_ID, result.id)
        assertEquals(user.firstName, result.firstName)
        assertEquals(user.lastName, result.lastName)
        assertEquals(user.type, result.type)
    }
}
