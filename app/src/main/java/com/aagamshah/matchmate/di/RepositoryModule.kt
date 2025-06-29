package com.aagamshah.matchmate.di

import com.aagamshah.matchmate.data.repository.ProfileRepositoryImpl
import com.aagamshah.matchmate.domain.repository.ProfileRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindUserRepository(userRepositoryImpl: ProfileRepositoryImpl): ProfileRepository

}