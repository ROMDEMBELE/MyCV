package com.diabeloop.sample.architecture.data.database

import androidx.room.TypeConverter
import com.diabeloop.sample.architecture.domain.user.DiabetesType

class Converter {
    @TypeConverter
    fun intToDiabetesType(ordinal: Int): DiabetesType = DiabetesType.values()[ordinal]

    @TypeConverter
    fun diabetesTypeToInt(diabetesType: DiabetesType): Int = diabetesType.ordinal

}
