package com.aagamshah.matchmate.data.repository

import com.aagamshah.matchmate.common.Resource
import com.aagamshah.matchmate.data.mapper.toProfileList
import com.aagamshah.matchmate.data.service.ApiService
import com.aagamshah.matchmate.domain.model.ProfileModel
import com.aagamshah.matchmate.domain.repository.ProfileRepository
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
) : ProfileRepository {

    override suspend fun fetchUsers(): Resource<List<ProfileModel>> {
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
}