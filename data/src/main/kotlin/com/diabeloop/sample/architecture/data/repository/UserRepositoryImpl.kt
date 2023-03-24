package com.diabeloop.sample.architecture.data.repository

import com.diabeloop.sample.architecture.data.database.Database
import com.diabeloop.sample.architecture.data.database.dao.UserDao
import com.diabeloop.sample.architecture.data.database.entity.toUserEntity
import com.diabeloop.sample.architecture.domain.user.User
import com.diabeloop.sample.architecture.domain.user.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(private val userDao: UserDao) : UserRepository {

    override suspend fun save(user: User): Boolean =
        userDao.insertOrReplace(user.toUserEntity()) != Database.NONE_ID

    override suspend fun delete(userId: Int): Boolean = userDao.deleteById(userId) == 1

    override fun getUser(id: Int?): Flow<User?> =
        userDao.getUserById(id ?: Database.DEFAULT_ID).map { it?.toUser() }

    override fun getList(): Flow<List<User>> =
        userDao.getUserList().map { it.map { userEntity -> userEntity.toUser() } }
}
