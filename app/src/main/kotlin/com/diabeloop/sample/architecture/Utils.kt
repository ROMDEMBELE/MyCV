package com.diabeloop.sample.architecture.common.extension

import android.content.Context
import com.diabeloop.architecture.R
import com.diabeloop.sample.architecture.domain.user.UserType
import com.diabeloop.sample.architecture.error.*

/**
 * [safeLet] ensure [block] running with non null parameters.
 *
 * Suppress("ComplexCondition")
 * The method check if multiple parameter are not null in one if statement.
 *
 * @param T1 type of parameter 1
 * @param T2 type of parameter 2
 * @param R
 * @param p1
 * @param p2
 * @param block
 * @return [block] with non null parameters or null
 */
@Suppress("ComplexCondition")
fun <T1 : Any, T2 : Any, R : Any> safeLet(
    p1: T1?,
    p2: T2?,
    block: (T1, T2) -> R?,
): R? =
    if (p1 != null && p2 != null) {
        block(p1, p2)
    } else {
        null
    }

fun String?.validateTextInput() = when {
    this.isNullOrEmpty() -> InputError.EMPTY_TEXT_ERROR
    this.matches(Regex(".*\\d.*")) -> InputError.MALFORMED_TEXT_ERROR
    else -> null
}

fun UserType.toString(context: Context): String = when (this) {
    UserType.TYPE_1 -> context.getString(R.string.diabetes_type_one)
    UserType.TYPE_2 -> context.getString(R.string.diabetes_type_two)
}
