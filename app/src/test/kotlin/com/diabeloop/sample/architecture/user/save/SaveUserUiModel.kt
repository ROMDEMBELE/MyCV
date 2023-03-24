package com.diabeloop.sample.architecture.user.save

import androidx.databinding.BaseObservable
import com.diabeloop.sample.architecture.domain.user.User
import com.diabeloop.sample.architecture.domain.user.UserType
import com.diabeloop.sample.architecture.error.InputError
import com.diabeloop.sample.architecture.safeLet
import com.diabeloop.sample.architecture.validateTextInput

class SaveUserUiModel(
    var id: Int? = null,
    firstName: String? = null,
    lastName: String? = null,
) : BaseObservable() {

    var firstName: String? = firstName
        set(value) {
            firstNameError = value.validateTextInput()
            field = value
            notifyChange()
        }

    var lastName: String? = lastName
        set(value) {
            lastNameError = value.validateTextInput()
            field = value
            notifyChange()
        }

    var lastNameError: InputError? = null

    var firstNameError: InputError? = null

    val isValidate: Boolean
        get() = lastNameError == null && firstNameError == null && firstName != null && lastName != null

    fun toUser(): User =
        safeLet(firstName, lastName) { safeFirstName, safeLastName ->
            User(
                id = id,
                firstName = safeFirstName,
                lastName = safeLastName,
                type = UserType.TYPE_1
            )
        } ?: run {
            throw ClassCastException("Unable to cast UserFormModel in User")
        }
}

fun User.toUserUiModel() = SaveUserUiModel(id, firstName, lastName)
