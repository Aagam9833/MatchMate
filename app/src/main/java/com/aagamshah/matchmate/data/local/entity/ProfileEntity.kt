package com.aagamshah.matchmate.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "profiles")
data class ProfileEntity(
    @PrimaryKey val id: String,
    val gender: String,
    val title: String,
    val firstName: String,
    val lastName: String,
    val dob: String,
    val age: Int,
    val registered: String,
    val pictureUrl: String,
    val nationality: String,
    val city: String,
    val isAccepted: Boolean? = null
)