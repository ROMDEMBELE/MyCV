package com.diabeloop.sample.architecture.binding

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.diabeloop.architecture.R
import com.diabeloop.sample.architecture.common.extension.toString
import com.diabeloop.sample.architecture.domain.user.UserType
import com.diabeloop.sample.architecture.error.InputError
import com.google.android.material.textfield.TextInputLayout

@BindingAdapter("error")
fun TextInputLayout.setError(inputError: InputError?) {
    error = when (inputError) {
        InputError.EMPTY_TEXT_ERROR -> context.getString(R.string.empty_text_error)
        InputError.MALFORMED_TEXT_ERROR -> context.getString(R.string.malformed_text_error)
        else -> null
    }
}

@BindingAdapter("userType")
fun TextView.setUserType(userType: UserType?) {
    text = (userType?.toString(context))
}
