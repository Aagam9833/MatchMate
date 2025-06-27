package com.aagamshah.matchmate.data.repository

import com.aagamshah.matchmate.data.service.ApiService
import com.aagamshah.matchmate.domain.model.UserModel
import com.aagamshah.matchmate.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
) : UserRepository {
    override suspend fun fetchUsers(): Flow<List<UserModel>> = flow {
        val data = apiService.getUsers(1)
    }

}