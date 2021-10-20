package com.diabeloop.sample.architecture.data.database.doa

import androidx.room.*
import com.diabeloop.sample.architecture.data.database.entity.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

//    @Insert(onConflict = OnConflictStrategy.ABORT)
//    suspend fun create(userProfile: UserEntity): Long
//
//    @Update(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun update(userProfile: UserEntity): Long
//
//    @Delete
//    suspend fun delete(userProfile: UserEntity): Long
//
//    @Transaction
//    @Query("SELECT * FROM user WHERE id=:id")
//    fun getUser(id: Int): Flow<UserEntity>
//
    @Transaction
    @Query("SELECT * FROM user")
    fun getUserList(): Flow<List<UserEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(userProfile: UserEntity)
}

