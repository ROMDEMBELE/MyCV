package com.diabeloop.sample.architecture.data.database

import androidx.room.TypeConverter
import com.diabeloop.sample.architecture.domain.user.UserType

class Converter {
    @TypeConverter
    fun intToUserType(ordinal: Int?): UserType? =
        ordinal?.let {
            UserType.values().getOrNull(ordinal)
        } ?: run {
            null
        }

    @TypeConverter
    fun userTypeToInt(ordinal: UserType?): Int? = ordinal?.ordinal
}
