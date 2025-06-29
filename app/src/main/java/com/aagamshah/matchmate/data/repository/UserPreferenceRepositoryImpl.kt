package com.aagamshah.matchmate.data.repository

import com.aagamshah.matchmate.data.local.dao.UserPreferenceDao
import com.aagamshah.matchmate.data.mapper.toDomain
import com.aagamshah.matchmate.data.mapper.toEntity
import com.aagamshah.matchmate.domain.model.UserPreferenceModel
import com.aagamshah.matchmate.domain.repository.UserPreferenceRepository
import javax.inject.Inject

class UserPreferenceRepositoryImpl @Inject constructor(
    private val dao: UserPreferenceDao
) : UserPreferenceRepository {

    override suspend fun savePreference(preference: UserPreferenceModel) {
        dao.savePreferences(preference.toEntity())
    }

    override suspend fun getPreference(): UserPreferenceModel? {
        return dao.getPreferences()?.toDomain()
    }

    override suspend fun deletePreference() {
        dao.deletePreferences()
    }
}