package com.aagamshah.matchmate.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.aagamshah.matchmate.data.local.entity.ProfileEntity

@Dao
interface ProfileDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProfiles(profiles: List<ProfileEntity>)

    @Query("UPDATE profiles SET isAccepted = :isAccepted WHERE id = :profileId")
    suspend fun updateIsAccepted(profileId: String, isAccepted: Boolean?)

    @Query("SELECT * FROM profiles")
    suspend fun getAllProfiles(): List<ProfileEntity>

}