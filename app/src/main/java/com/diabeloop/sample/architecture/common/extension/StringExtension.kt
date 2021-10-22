package com.diabeloop.sample.architecture.common.extension

import com.diabeloop.sample.architecture.error.*


fun String?.validateTextInput() = when {
    this.isNullOrEmpty() -> InputError.EMPTY_TEXT_ERROR
    this.matches(Regex(".*\\d.*")) -> InputError.MALFORMED_TEXT_ERROR
    else -> null
}