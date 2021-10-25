package com.diabeloop.sample.architecture.user

import com.diabeloop.sample.architecture.domain.user.DiabetesType
import com.diabeloop.sample.architecture.domain.user.User

class UserItemModel(val id: Int, val firstName: String, val lastName: String, val diabetesType: DiabetesType?)

fun User.toUserItem() = id?.let {
    UserItemModel(it, firstName, lastName, diabetesType)
} ?: run {
    throw ClassCastException("Unable to cast User in UserItemModel")
}