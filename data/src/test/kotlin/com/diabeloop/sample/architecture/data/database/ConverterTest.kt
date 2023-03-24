package com.diabeloop.sample.architecture.data.database

import com.diabeloop.sample.architecture.domain.user.UserType
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test

internal class ConverterTest {

    @Test
    fun intToUserType_shall_returnUserType() {
        // Arrange
        val value = UserType.values().random()
        val sut = Converter()

        // Act
        val result = sut.intToUserType(value.ordinal)

        // Assert
        assertEquals(value, result)
    }

    @Test
    fun intToUserType_shall_returnNull() {
        // Arrange
        val sut = Converter()

        // Act
        val result = sut.intToUserType(100)

        // Assert
        assertNull(result)
    }

    @Test
    fun userTypeToInt_shall_returnOrdinal() {
        // Arrange
        val userType = UserType.values().random()
        val sut = Converter()

        // Act
        val result = sut.userTypeToInt(userType)

        // Assert
        assertEquals(userType.ordinal, result)
    }

    @Test
    fun userTypeToInt_shall_returnNull() {
        // Arrange
        val userType = null
        val sut = Converter()

        // Act
        val result = sut.userTypeToInt(userType)

        // Assert
        assertNull(result)
    }
}
