package com.diabeloop.sample.architecture.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.diabeloop.sample.architecture.domain.user.DiabetesType
import com.diabeloop.sample.architecture.domain.user.User

@Entity(tableName = "user")
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    val firstName: String,

    val lastName: String,

    var diabetesType: DiabetesType?
) {
    fun toUser() = User(id, firstName, lastName, diabetesType)
}

fun User.toUserEntity() = UserEntity(id, firstName, lastName, diabetesType)
