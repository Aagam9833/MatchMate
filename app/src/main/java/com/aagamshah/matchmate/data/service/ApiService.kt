package com.aagamshah.matchmate.data.service

import com.aagamshah.matchmate.data.remote.UserResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET(".")
    suspend fun getUsers(@Query("page") page: Int): Response<UserResponse>

}