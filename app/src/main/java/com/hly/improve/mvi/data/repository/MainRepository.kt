package com.hly.improve.mvi.data.repository

import com.hly.improve.mvi.data.api.ApiService

class MainRepository(private val apiService: ApiService) {

    suspend fun getUsers() = apiService.getUsers()
}