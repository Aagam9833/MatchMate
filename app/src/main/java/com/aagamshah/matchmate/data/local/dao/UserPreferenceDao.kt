package com.aagamshah.matchmate.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.aagamshah.matchmate.data.local.entity.UserPreferenceEntity

@Dao
interface UserPreferenceDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun savePreferences(preference: UserPreferenceEntity)

    @Query("SELECT * FROM user_preferences WHERE id = 0")
    suspend fun getPreferences(): UserPreferenceEntity?
}