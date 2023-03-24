package com.diabeloop.samble.architecture.data.database.dao

import android.content.Context
import androidx.room.Room
import androidx.sqlite.db.SimpleSQLiteQuery
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.diabeloop.sample.architecture.data.database.Database
import com.diabeloop.sample.architecture.data.database.dao.UserDao
import com.diabeloop.sample.architecture.data.database.entity.UserEntity
import com.diabeloop.sample.architecture.domain.user.UserType
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

/**
 * Test class for [UserDao].
 */
@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class UserDaoTest {

    private lateinit var sut: UserDao
    private lateinit var db: Database

    @Before
    fun setup() {
        val context: Context = ApplicationProvider.getApplicationContext()
        db = Room.inMemoryDatabaseBuilder(
            context,
            Database::class.java
        )
            .allowMainThreadQueries()
            .build()
        sut = db.userDoa()
    }

    @After
    @Throws(IOException::class)
    fun cleanup() {
        db.close()
    }

    @Test
    fun insertOrReplace_shall_createNewUserEntry() = runBlocking {
        // Arrange
        val entity = UserEntity(
            firstName = "John",
            lastName = "Wick",
            type = UserType.TYPE_1,
        )

        // Act
        val result = sut.insertOrReplace(entity)

        // Assert
        val cursor = db.query(SimpleSQLiteQuery("SELECT * FROM user"))
        cursor.moveToFirst()
        assertEquals(1, cursor.count)
        assertEquals(result.toInt(), cursor.getInt(cursor.getColumnIndex("id")))
        assertEquals(entity.firstName, cursor.getLong(cursor.getColumnIndex("firstName")))
        assertEquals(entity.lastName, cursor.getInt(cursor.getColumnIndex("lastName")))
        assertEquals(entity.type.name, cursor.getString(cursor.getColumnIndex("type")))
    }

    @Test
    fun insertOrReplace_shall_updateUserEntry() = runBlocking {
        // Arrange
        val entity = UserEntity(
            firstName = "John",
            lastName = "Wick",
            type = UserType.TYPE_1,
        )
        sut.insertOrReplace(entity)

        // Act
        val result = sut.insertOrReplace(entity)

        // Assert
        val cursor = db.query(SimpleSQLiteQuery("SELECT * FROM user"))
        cursor.moveToFirst()
        assertEquals(1, cursor.count)
        assertEquals(result.toInt(), cursor.getInt(cursor.getColumnIndex("id")))
        assertEquals(entity.firstName, cursor.getLong(cursor.getColumnIndex("firstName")))
        assertEquals(entity.lastName, cursor.getInt(cursor.getColumnIndex("lastName")))
        assertEquals(entity.type.name, cursor.getString(cursor.getColumnIndex("type")))
    }

    @Test
    fun getUserList_shall_returnAFlowOfUserList() = runBlocking {
        // Arrange
        val user1 = UserEntity(
            firstName = "John",
            lastName = "Wick",
            type = UserType.TYPE_1,
        )
        val user2 = UserEntity(
            firstName = "Jean",
            lastName = "Bobo",
            type = UserType.TYPE_2,
        )
        sut.insertOrReplace(user1)
        sut.insertOrReplace(user2)

        // Act
        val result = sut.getUserList().first()

        // Assert
        assertEquals(user1.firstName, result[0].firstName)
        assertEquals(user1.lastName, result[0].lastName)
        assertEquals(user1.type, result[0].type)

        assertEquals(user2.firstName, result[1].firstName)
        assertEquals(user2.lastName, result[1].lastName)
        assertEquals(user2.type, result[1].type)
    }

    fun getUserById_shall_returnAFlowOfUser() = runBlocking {
        // Arrange
        val user = UserEntity(
            firstName = "Jean",
            lastName = "Bobo",
            type = UserType.TYPE_2,
        )
        val id = sut.insertOrReplace(user)

        // Act
        val result = sut.getUserById(id.toInt()).first()

        // Assert
        assertEquals(user.firstName, result?.firstName)
        assertEquals(user.lastName, result?.lastName)
        assertEquals(user.type, result?.type)
    }
}
