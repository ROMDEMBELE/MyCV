package com.diabeloop.sample.architecture.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.diabeloop.sample.architecture.data.database.Database
import com.diabeloop.sample.architecture.domain.user.User
import com.diabeloop.sample.architecture.domain.user.UserType

/**
 * Data class representing user in database.
 *
 * @property firstName
 * @property lastName
 * @property type
 */
@Entity(tableName = "user")
data class UserEntity(
    val firstName: String,
    val lastName: String,
    var type: UserType,
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = Database.DEFAULT_ID

    fun toUser() = User(id, firstName, lastName, type)
}

fun User.toUserEntity(): UserEntity {
    val userEntity = UserEntity(firstName, lastName, type)
    id?.let {
        userEntity.id = it
    }
    return userEntity
}
