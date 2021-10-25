package com.diabeloop.sample.architecture.domain.user

import kotlinx.coroutines.flow.Flow


interface UserRepository {
    suspend fun save(user: User) : Int
    fun getList(): Flow<List<User>>
}
