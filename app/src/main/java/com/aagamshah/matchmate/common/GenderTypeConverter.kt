package com.aagamshah.matchmate.common

import androidx.room.TypeConverter
import com.aagamshah.matchmate.data.remote.Gender

class GenderTypeConverter {

    @TypeConverter
    fun fromGenderPreference(value: Gender): String {
        return value.name
    }

    @TypeConverter
    fun toGenderPreference(value: String): Gender {
        return Gender.valueOf(value)
    }
}