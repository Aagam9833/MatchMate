package com.aagamshah.matchmate.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.aagamshah.matchmate.common.GenderTypeConverter
import com.aagamshah.matchmate.data.local.dao.ProfileDao
import com.aagamshah.matchmate.data.local.dao.UserPreferenceDao
import com.aagamshah.matchmate.data.local.entity.ProfileEntity
import com.aagamshah.matchmate.data.local.entity.UserPreferenceEntity

@Database(entities = [UserPreferenceEntity::class, ProfileEntity::class], version = 1)
@TypeConverters(GenderTypeConverter::class)
abstract class MatchMateDatabase : RoomDatabase() {
    abstract fun userPreferenceDao(): UserPreferenceDao
    abstract fun profileDao(): ProfileDao
}
