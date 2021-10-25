package com.diabeloop.sample.architecture.common.extension

import android.content.res.Resources
import com.diabeloop.architecture.R
import com.diabeloop.sample.architecture.domain.user.DiabetesType

fun DiabetesType.displayMessage(resources: Resources): String {
    return when(this) {
        DiabetesType.TYPE_1 -> resources.getString(R.string.diabetes_type_one)
        DiabetesType.TYPE_2 -> resources.getString(R.string.diabetes_type_two)
    }
}

fun DiabetesType.displayIcon(resources: Resources): Int {
    return when(this) {
        DiabetesType.TYPE_1 -> R.drawable.ic_diabetes_type_one
        DiabetesType.TYPE_2 -> R.drawable.ic_diabetes_type_two
    }
}
