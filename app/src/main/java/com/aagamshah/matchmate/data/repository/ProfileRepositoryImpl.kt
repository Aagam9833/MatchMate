package com.aagamshah.matchmate.data.repository

import com.aagamshah.matchmate.common.Resource
import com.aagamshah.matchmate.data.local.dao.ProfileDao
import com.aagamshah.matchmate.data.mapper.toDomainList
import com.aagamshah.matchmate.data.mapper.toEntityList
import com.aagamshah.matchmate.data.mapper.toProfileList
import com.aagamshah.matchmate.data.service.ApiService
import com.aagamshah.matchmate.domain.model.ProfileModel
import com.aagamshah.matchmate.domain.repository.ProfileRepository
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val profileDao: ProfileDao
) : ProfileRepository {

    override suspend fun fetchProfiles(): Resource<List<ProfileModel>> {
        return try {
            val response = apiService.getUsers(1)
            if (response.isSuccessful) {
                val results = response.body()?.results.orEmpty()
                Resource.Success(results.toProfileList())
            } else {
                Resource.Error("API Error: ${response.code()}")
            }
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Unknown error")
        }
    }

    override suspend fun fetchOfflineProfiles(): List<ProfileModel> {
        return profileDao.getAllProfiles().toDomainList()
    }

    override suspend fun saveProfiles(users: List<ProfileModel>) {
        profileDao.insertProfiles(users.toEntityList())
    }

    override suspend fun updateProfileChoice(profileId: String, isAccepted: Boolean?) {
        profileDao.updateIsAccepted(profileId, isAccepted)
    }
}