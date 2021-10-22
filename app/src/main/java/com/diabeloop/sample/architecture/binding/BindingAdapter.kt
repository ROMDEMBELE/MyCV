package com.diabeloop.sample.architecture.binding

import androidx.databinding.BindingAdapter
import com.diabeloop.architecture.R
import com.diabeloop.sample.architecture.error.InputError
import com.google.android.material.textfield.TextInputLayout

@BindingAdapter("app:error")
fun setError(textInputLayout: TextInputLayout, inputError: InputError?) {
    val resource = textInputLayout.context.resources
    textInputLayout.error = when (inputError) {
        InputError.EMPTY_TEXT_ERROR -> resource.getString(R.string.empty_text_error)
        InputError.MALFORMED_TEXT_ERROR -> resource.getString(R.string.malformed_text_error)
        else -> null
    }
}
