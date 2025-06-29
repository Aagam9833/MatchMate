package com.aagamshah.matchmate.data.service

import com.aagamshah.matchmate.data.remote.ProfileResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET(".")
    suspend fun getUsers(
        @Query("page") page: Int,
        @Query("results") results: Int = 10
    ): Response<ProfileResponse>

}
