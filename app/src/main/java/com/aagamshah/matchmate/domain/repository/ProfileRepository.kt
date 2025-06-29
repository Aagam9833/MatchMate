package com.aagamshah.matchmate.domain.repository

import com.aagamshah.matchmate.common.Resource
import com.aagamshah.matchmate.domain.model.ProfileModel
import javax.inject.Singleton

@Singleton
interface ProfileRepository {

    suspend fun fetchUsers(): Resource<List<ProfileModel>>

}