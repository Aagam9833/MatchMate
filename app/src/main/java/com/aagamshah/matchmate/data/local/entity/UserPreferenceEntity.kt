package com.aagamshah.matchmate.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.aagamshah.matchmate.common.Country
import com.aagamshah.matchmate.data.remote.Gender

@Entity(tableName = "user_preferences")
data class UserPreferenceEntity(
    @PrimaryKey val id: Int = 0,
    val genderPreference: Gender,
    val age: Int,
    val country: Country
)