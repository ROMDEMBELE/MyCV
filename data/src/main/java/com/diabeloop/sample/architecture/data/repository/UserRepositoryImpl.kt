package com.diabeloop.sample.architecture.data.repository

import com.diabeloop.sample.architecture.data.database.doa.UserDao
import com.diabeloop.sample.architecture.data.database.entity.toUserEntity
import com.diabeloop.sample.architecture.domain.user.User
import com.diabeloop.sample.architecture.domain.user.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserRepositoryImpl(private val userDao: UserDao) : UserRepository {

    override suspend fun save(user: User) = userDao.save(user.toUserEntity())

    override fun getList(): Flow<List<User>> =
        userDao.getUserList().map { it.map { userEntity -> userEntity.toUser() } }

    override fun getUser(id: Int): Flow<User> =
        userDao.getUser(id).map { it.toUser() }
    // FIXME for getUser() : Flow<User> use .catch to return null User
    // exception : EmptyBase, NoResultForId
}
