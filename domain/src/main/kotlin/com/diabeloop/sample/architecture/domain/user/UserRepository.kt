package com.diabeloop.sample.architecture.domain.user

import kotlinx.coroutines.flow.Flow

/**
 * Interface Repository to Manager [User].
 */
interface UserRepository {

    suspend fun save(user: User): Boolean

    suspend fun delete(userId: Int): Boolean

    fun getList(): Flow<List<User>>

    fun getUser(id: Int?): Flow<User?>
}
