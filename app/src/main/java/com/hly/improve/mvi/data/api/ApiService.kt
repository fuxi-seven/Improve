package com.hly.improve.mvi.data.api

import com.hly.improve.mvi.util.UserInfo
import retrofit2.http.GET

interface ApiService {

    @GET("users")
    suspend fun getUsers(): UserInfo
}