package com.diabeloop.sample.architecture.data.database

import androidx.room.TypeConverter
import com.diabeloop.sample.architecture.domain.user.DiabetesType

class Converter {
    @TypeConverter
    fun intToDiabetesType(ordinal: Int?): DiabetesType? =
        ordinal?.let {
            DiabetesType.values().getOrNull(ordinal)
        } ?: run {
            null
        }

    @TypeConverter
    fun diabetesTypeToInt(ordinal: DiabetesType?): Int? = ordinal?.ordinal
}
