package com.aagamshah.matchmate.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.aagamshah.matchmate.common.CountryTypeConverter
import com.aagamshah.matchmate.common.GenderTypeConverter
import com.aagamshah.matchmate.data.local.dao.ProfileDao
import com.aagamshah.matchmate.data.local.dao.UserPreferenceDao
import com.aagamshah.matchmate.data.local.entity.ProfileEntity
import com.aagamshah.matchmate.data.local.entity.UserPreferenceEntity

@Database(entities = [UserPreferenceEntity::class, ProfileEntity::class], version = 2)
@TypeConverters(GenderTypeConverter::class, CountryTypeConverter::class)
abstract class MatchMateDatabase : RoomDatabase() {
    abstract fun userPreferenceDao(): UserPreferenceDao
    abstract fun profileDao(): ProfileDao

    companion object {
        val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(db: SupportSQLiteDatabase) {
                db.execSQL(
                    "ALTER TABLE user_preferences ADD COLUMN country TEXT NOT NULL DEFAULT 'IN'"
                )
            }
        }
    }

}
