package com.aagamshah.matchmate.domain.model

import com.aagamshah.matchmate.common.Country
import com.aagamshah.matchmate.data.remote.Gender

data class UserPreferenceModel(
    val genderPreference: Gender,
    val age: Int,
    val country: Country
)
