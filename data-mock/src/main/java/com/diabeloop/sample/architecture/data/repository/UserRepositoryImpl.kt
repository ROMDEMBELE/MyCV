package com.diabeloop.sample.architecture.data.repository

import com.diabeloop.sample.architecture.domain.user.DiabetesType
import com.diabeloop.sample.architecture.domain.user.User
import com.diabeloop.sample.architecture.domain.user.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class UserRepositoryImpl : UserRepository {
    override fun save(user: User) {

    }

    override fun getList(): Flow<List<User>> = flow {
        emit(
            listOf(
                User("Jone", "Doe"),
                User("Dragon", "3_têtes", DiabetesType.TYPE_2),
                User("Gilles", "Gaillardon", DiabetesType.TYPE_1)
            )
        )
    }
}
