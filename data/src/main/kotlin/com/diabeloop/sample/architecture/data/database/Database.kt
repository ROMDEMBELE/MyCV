package com.diabeloop.sample.architecture.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.diabeloop.sample.architecture.data.database.dao.UserDao
import com.diabeloop.sample.architecture.data.database.entity.UserEntity

@Database(entities = [UserEntity::class], version = 1, exportSchema = false)
@TypeConverters(Converter::class)
abstract class Database : RoomDatabase() {

    abstract fun userDoa(): UserDao

    companion object {
        const val DATABASE_NAME: String = "database"

        const val DEFAULT_ID = 0

        const val NONE_ID = -1L
    }
}
