package com.diabeloop.sample.architecture.user.list

import com.diabeloop.sample.architecture.domain.user.User
import com.diabeloop.sample.architecture.domain.user.UserType

/**
 * UiModel for user list items.
 *
 * @property id
 * @property firstName
 * @property lastName
 * @property type
 */
class UserItemModel(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val type: UserType,
)

/**
 * Mapping method for [User] to [UserItemModel].
 */
fun User.toUserItem() = id?.let {
    UserItemModel(it, firstName, lastName, type)
} ?: run {
    throw ClassCastException("Unable to cast User in UserItemModel")
}
