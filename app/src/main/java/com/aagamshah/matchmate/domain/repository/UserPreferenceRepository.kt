package com.aagamshah.matchmate.domain.repository

import com.aagamshah.matchmate.domain.model.UserPreferenceModel

interface UserPreferenceRepository {
    suspend fun savePreference(preference: UserPreferenceModel)
    suspend fun getPreference(): UserPreferenceModel?
}