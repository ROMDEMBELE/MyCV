package com.diabeloop.sample.architecture.data.database.doa

import androidx.room.*
import com.diabeloop.sample.architecture.data.database.entity.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Transaction
    @Query("SELECT * FROM user")
    fun getUserList(): Flow<List<UserEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(userProfile: UserEntity)

    @Query("SELECT * FROM user WHERE id=:id")
    fun getUser(id: Int): Flow<UserEntity>
}

