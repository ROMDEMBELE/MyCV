package com.diabeloop.sample.architecture.binding

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.diabeloop.architecture.R
import com.diabeloop.sample.architecture.domain.user.UserType
import com.diabeloop.sample.architecture.error.InputError
import com.diabeloop.sample.architecture.toString
import com.google.android.material.textfield.TextInputLayout

/**
 * Binding adapter to display error in TextInputLayout.
 *
 * @param inputError
 */
@BindingAdapter("error")
fun TextInputLayout.setError(inputError: InputError?) {
    error = when (inputError) {
        InputError.EMPTY_TEXT_ERROR -> context.getString(R.string.empty_text_error)
        InputError.MALFORMED_TEXT_ERROR -> context.getString(R.string.malformed_text_error)
        else -> null
    }
}

/**
 * Binding adapter to display [UserType] in [TextView].
 *
 * @param inputError
 */
@BindingAdapter("userType")
fun TextView.setUserType(userType: UserType?) {
    text = userType?.toString(context)
}
