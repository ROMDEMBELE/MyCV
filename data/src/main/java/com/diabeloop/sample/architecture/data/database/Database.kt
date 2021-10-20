package com.diabeloop.sample.architecture.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.diabeloop.sample.architecture.data.database.doa.UserDao
import com.diabeloop.sample.architecture.data.database.entity.UserEntity

@Database(entities = [UserEntity::class], version = 1, exportSchema = false)
@TypeConverters(Converter::class)
abstract class Database : RoomDatabase() {

    abstract fun userDoa(): UserDao

    companion object {
        const val DATABASE_NAME: String = "database"
    }
}
