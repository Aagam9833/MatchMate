package com.aagamshah.matchmate.common

import androidx.room.TypeConverter

class CountryTypeConverter {
    @TypeConverter
    fun fromCountry(value: Country): String = value.name

    @TypeConverter
    fun toCountry(value: String): Country = Country.valueOf(value)
}