package com.aagamshah.matchmate.di

import android.app.Application
import androidx.room.Room
import com.aagamshah.matchmate.common.Constants
import com.aagamshah.matchmate.data.local.MatchMateDatabase
import com.aagamshah.matchmate.data.local.dao.ProfileDao
import com.aagamshah.matchmate.data.local.dao.UserPreferenceDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun provideBeatODatabase(application: Application) =
        Room.databaseBuilder(
            application,
            MatchMateDatabase::class.java,
            Constants.DATABASE
        ).addMigrations(MatchMateDatabase.MIGRATION_1_2)
            .build()

    @Provides
    fun provideUserPreferenceDao(db: MatchMateDatabase): UserPreferenceDao {
        return db.userPreferenceDao()
    }

    @Provides
    fun provideProfileDao(db: MatchMateDatabase): ProfileDao {
        return db.profileDao()
    }

}