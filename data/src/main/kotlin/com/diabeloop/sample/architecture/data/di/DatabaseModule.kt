package com.diabeloop.sample.architecture.data.di

import android.content.Context
import androidx.room.Room
import com.diabeloop.sample.architecture.data.database.Database
import com.diabeloop.sample.architecture.data.database.dao.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Module of Database.
 */
@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): Database {
        return Room.databaseBuilder(
            context,
            Database::class.java,
            Database.DATABASE_NAME
        )
            .build()
    }

    @Singleton
    @Provides
    fun provideUserDao(database: Database): UserDao = database.userDoa()
}
