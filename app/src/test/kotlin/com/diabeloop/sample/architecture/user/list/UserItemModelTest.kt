package com.diabeloop.sample.architecture.user.list

import com.diabeloop.sample.architecture.domain.user.User
import com.diabeloop.sample.architecture.domain.user.UserType
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

/**
 * Test class for [UserItemModel].
 */
class UserItemModelTest {

    @Test
    fun user_withoutId_toUserItem_shallThrowAnException() {
        // Arrange
        val sut = User(
            firstName = "John",
            lastName = "Wick",
            type = UserType.TYPE_1
        )

        // Act
        assertThrows<ClassCastException> { sut.toUserItem() }
    }

    @Test
    fun user_withId_toUserItem_shallReturnUserItem() {
        // Arrange
        val sut = User(
            id = 1,
            firstName = "John",
            lastName = "Wick",
            type = UserType.TYPE_1
        )

        // Act
        val result = sut.toUserItem()

        // Assert
        assertEquals(sut.id, result.id)
        assertEquals(sut.firstName, result.firstName)
        assertEquals(sut.lastName, result.lastName)
        assertEquals(sut.type, result.type)
    }
}
