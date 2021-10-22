package com.diabeloop.sample.architecture.user

import androidx.databinding.BaseObservable
import com.diabeloop.sample.architecture.common.extension.validateTextInput
import com.diabeloop.sample.architecture.error.InputError
import com.diabeloop.sample.architecture.domain.user.User

class UserFormModel(
    val id: Int? = null,
    firstName: String? = null,
    lastName: String? = null
) : BaseObservable() {

    var firstName: String? = null
        set(value) {
            firstNameError = value.validateTextInput()
            field = value
            notifyChange()
        }

    var lastName: String? = null
        set(value) {
            lastNameError = value.validateTextInput()
            field = value
            notifyChange()
        }

    var lastNameError: InputError? = null
    var firstNameError: InputError? = null



    fun toUser() : User {
        val firstName = this.firstName
        val lastName = this.lastName
        return if (id != null && firstName != null && lastName != null) {
            User(id, firstName, lastName, null)
        } else {
            throw ClassCastException("Unable to cast UserFormModel in User")
        }
    }
}