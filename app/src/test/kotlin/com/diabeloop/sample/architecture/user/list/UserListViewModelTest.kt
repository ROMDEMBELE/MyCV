@file:OptIn(ExperimentalCoroutinesApi::class)

package com.diabeloop.sample.architecture.user.list

import com.diabeloop.sample.architecture.domain.user.User
import com.diabeloop.sample.architecture.domain.user.UserRepository
import com.diabeloop.sample.architecture.domain.user.UserType
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit4.MockKRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import org.junit.jupiter.api.Assertions.assertEquals

class UserListViewModelTest {

    @get:Rule
    val mockkRule = MockKRule(this)

    @MockK
    lateinit var userRepository: UserRepository

    @Test
    fun userList_shall_returnFlowOfUserItemList() = runTest {
        // Arrange
        val user = User(1, "D", "R", UserType.TYPE_1)
        val sut = UserListViewModel(userRepository)
        every { userRepository.getList() } returns flowOf(listOf(user))

        // Act
        val result = sut.userList.first()

        // Assert
        assertEquals(1, result.size)
        assertEquals(user.id, result[0].id)
        assertEquals(user.firstName, result[0].firstName)
        assertEquals(user.lastName, result[0].lastName)
        assertEquals(user.type, result[0].type)
    }

    @Test
    fun deleteUser_shall_callDelete() = runTest {
        // Arrange
        val userId = 1
        coEvery { userRepository.delete(userId) } returns true
        val sut = UserListViewModel(userRepository)

        // Act
        val result = sut.deleteUser(userId)

        // Assert
        assertEquals(true, result)
        coVerify { userRepository.delete(userId) }
    }
}
