package com.aagamshah.matchmate.data.repository

import com.aagamshah.matchmate.common.Resource
import com.aagamshah.matchmate.data.local.dao.ProfileDao
import com.aagamshah.matchmate.data.mapper.toDomainList
import com.aagamshah.matchmate.data.mapper.toEntityList
import com.aagamshah.matchmate.data.mapper.toProfileList
import com.aagamshah.matchmate.data.service.ApiService
import com.aagamshah.matchmate.data.utils.ErrorHandler
import com.aagamshah.matchmate.domain.model.ProfileModel
import com.aagamshah.matchmate.domain.repository.ProfileRepository
import java.io.IOException
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
                Resource.Error(ErrorHandler.ApiError("API Error: ${response.code()}"))
            }
        } catch (e: IOException) {
            Resource.Error(ErrorHandler.NetworkError())
        } catch (e: Exception) {
            Resource.Error(ErrorHandler.UnknownError(e.message ?: "Unknown"))
        }
    }

    override suspend fun fetchOfflineProfiles(): List<ProfileModel> {
        return try {
            profileDao.getAllProfiles().toDomainList()
        } catch (e: Exception) {
            emptyList()
        }
    }

    override suspend fun saveProfiles(users: List<ProfileModel>): Resource<Unit> {
        return try {
            profileDao.insertProfiles(users.toEntityList())
            Resource.Success(Unit)
        } catch (e: Exception) {
            Resource.Error(ErrorHandler.DbError("DB insert failed: ${e.localizedMessage}"))
        }
    }

    override suspend fun updateProfileChoice(
        profileId: String,
        isAccepted: Boolean?
    ): Resource<Unit> {
        return try {
            profileDao.updateIsAccepted(profileId, isAccepted)
            Resource.Success(Unit)
        } catch (e: Exception) {
            Resource.Error(ErrorHandler.DbError("DB update failed: ${e.localizedMessage}"))
        }
    }
}