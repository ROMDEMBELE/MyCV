package com.diabeloop.sample.architecture.data.di

import com.diabeloop.sample.architecture.data.repository.UserRepositoryImpl
import com.diabeloop.sample.architecture.domain.user.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

/**
 * Module of repositories.
 */
@Module
@InstallIn(ViewModelComponent::class)
interface RepositoryModule {

    @Binds
    @ViewModelScoped
    fun provideUserRepository(repository: UserRepositoryImpl): UserRepository
}
