package com.diabeloop.sample.architecture.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.diabeloop.sample.architecture.domain.user.DiabetesType
import com.diabeloop.sample.architecture.domain.user.User

@Entity(tableName = "user")
data class UserEntity(

    val firstName: String,

    val lastName: String,

    var diabetesType: DiabetesType?
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

    fun toUser() = User(id, firstName, lastName, diabetesType)
}

fun User.toUserEntity() :UserEntity {
    val userEntity = UserEntity(firstName, lastName, diabetesType)
    id?.let {
        userEntity.id=it
    }
    return userEntity
}

    id?.let {
    UserEntity(firstName, lastName, diabetesType).apply { id = it }
} ?: run {
    UserEntity(firstName, lastName, diabetesType)
}


UserEntity(firstName, lastName, diabetesType).apply { id.let { this@toUserEntity.id } }


