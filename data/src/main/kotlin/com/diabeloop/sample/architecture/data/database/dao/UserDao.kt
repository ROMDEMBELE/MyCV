package com.diabeloop.sample.architecture.data.database.dao

import androidx.room.*
import com.diabeloop.sample.architecture.data.database.entity.UserEntity
import kotlinx.coroutines.flow.Flow

/**
 * DAO for [UserEntity].
 */
@Dao
interface UserDao {

    /**
     * Get the all the user entry from database.
     */
    @Query("SELECT * FROM user ORDER BY id DESC")
    fun getUserList(): Flow<List<UserEntity>>

    /**
     * Insert or replace user entry in database.
     * @return id of the entry.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrReplace(entity: UserEntity): Long

    /**
     * Delete user entry corresponding to the id from database.
     * @return the number of deleted row.
     */
    @Query("DELETE FROM user WHERE id=:id")
    suspend fun deleteById(id: Int): Int

    /**
     * Look for the user entry corresponding to the id in database.
     * @return [UserEntity] or null is there is no correspondence.
     */
    @Query("SELECT * FROM user WHERE id=:id")
    fun getUserById(id: Int): Flow<UserEntity?>
}
