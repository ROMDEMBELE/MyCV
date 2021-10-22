package com.diabeloop.sample.architecture.data.di

import com.diabeloop.sample.architecture.data.database.doa.UserDao
import com.diabeloop.sample.architecture.data.repository.UserRepositoryImpl
import com.diabeloop.sample.architecture.domain.user.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {
    @Provides
    @Singleton
    fun provideMoviesRepository(userDao: UserDao): UserRepository = UserRepositoryImpl(userDao)
}
