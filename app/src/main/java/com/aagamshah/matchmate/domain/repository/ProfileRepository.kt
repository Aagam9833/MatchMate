package com.aagamshah.matchmate.domain.repository

import com.aagamshah.matchmate.common.Resource
import com.aagamshah.matchmate.domain.model.ProfileModel
import javax.inject.Singleton

@Singleton
interface ProfileRepository {

    suspend fun fetchProfiles(): Resource<List<ProfileModel>>
    suspend fun fetchOfflineProfiles(): List<ProfileModel>
    suspend fun saveProfiles(users: List<ProfileModel>)
    suspend fun updateProfileChoice(profileId: String, isAccepted: Boolean?)

}