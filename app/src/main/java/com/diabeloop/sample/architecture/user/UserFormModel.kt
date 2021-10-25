package com.diabeloop.sample.architecture.user

import androidx.databinding.BaseObservable
import com.diabeloop.sample.architecture.common.extension.validateTextInput
import com.diabeloop.sample.architecture.domain.user.DiabetesType
import com.diabeloop.sample.architecture.domain.user.User
import com.diabeloop.sample.architecture.error.InputError

class UserFormModel(
    var id: Int? = null,
    firstName: String? = null,
    lastName: String? = null,
    diabetesType: DiabetesType? = null
) : BaseObservable() {

    var firstName: String? = firstName
        set(value) {
            firstNameError = value.validateTextInput()
            field = value
            validate()
            notifyChange()
        }

    var lastName: String? = lastName
        set(value) {
            lastNameError = value.validateTextInput()
            field = value
            validate()
            notifyChange()
        }

    var diabetesType: DiabetesType? = diabetesType
        set (value) {
            field = value
            validate()
            notifyChange()
        }

    var lastNameError: InputError? = null
    var firstNameError: InputError? = null

    var isValidate: Boolean = false

    fun toUser(): User {
        val firstName = this.firstName
        val lastName = this.lastName
        return if (firstName != null && lastName != null) {
            User(id, firstName, lastName, diabetesType)
        } else {
            throw ClassCastException("Unable to cast UserFormModel in User")
        }
    }

    private fun validate() {
        isValidate = lastNameError == null
        isValidate = firstNameError == null && isValidate
        isValidate = firstName != null && isValidate
        isValidate = lastName != null && isValidate
    }

//    private fun checkError(): Boolean =
//        when {
//            firstName == null -> true
//            firstNameError != null -> true
//            lastName == null -> true
//            lastNameError != null -> true
//            else -> false
//        }

}

fun User.toUserFormModel() = UserFormModel(id, firstName, lastName, diabetesType)
