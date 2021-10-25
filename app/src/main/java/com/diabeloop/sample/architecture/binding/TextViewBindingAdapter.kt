package com.diabeloop.sample.architecture.binding

import android.widget.AutoCompleteTextView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener
import com.diabeloop.architecture.R
import com.diabeloop.sample.architecture.common.extension.displayMessage
import com.diabeloop.sample.architecture.domain.user.DiabetesType
import com.diabeloop.sample.architecture.error.InputError
import com.diabeloop.sample.architecture.user.DiabetesTypeAdapter
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

@BindingAdapter("app:diabetesType")
fun setDiabetesType(textView: TextView, diabetesType: DiabetesType?) {
    textView.text = (diabetesType?.displayMessage(textView.resources))
}

@InverseBindingAdapter(attribute = "app:diabetesType")
fun getDiabetesType(autoCompleteTextView: AutoCompleteTextView): DiabetesType? {
    return (autoCompleteTextView.adapter as? DiabetesTypeAdapter)?.selectedDiabetesType
}

@BindingAdapter("diabetesTypeAttrChanged")
fun AutoCompleteTextView.setListener(listener: InverseBindingListener) {
    setOnItemClickListener { _, _, _, _ -> listener.onChange() }
}