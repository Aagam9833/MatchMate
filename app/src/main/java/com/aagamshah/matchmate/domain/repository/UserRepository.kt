package com.aagamshah.matchmate.domain.repository

import com.aagamshah.matchmate.domain.model.UserModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Singleton

@Singleton
interface UserRepository {

    suspend fun fetchUsers(): Flow<List<UserModel>>

}