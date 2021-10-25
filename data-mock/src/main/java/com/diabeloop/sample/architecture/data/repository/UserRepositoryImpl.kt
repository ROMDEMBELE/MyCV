package com.diabeloop.sample.architecture.data.repository

import com.diabeloop.sample.architecture.domain.user.DiabetesType
import com.diabeloop.sample.architecture.domain.user.User
import com.diabeloop.sample.architecture.domain.user.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class UserRepositoryImpl : UserRepository {
    override suspend fun save(user: User) {

    }

    override fun getList(): Flow<List<User>> = flow {
        emit(
            listOf(
                User(0, "Jone", "Doe"),
                User(1, "Dragon", "3_têtes", DiabetesType.TYPE_2),
                User(2, "Gilles", "Gaillardon", DiabetesType.TYPE_1)
            )
        )
    }

    override fun getUser(id: Int): Flow<User> = flow {
        emit(User(id, "John", "Doe"))
    }
}
