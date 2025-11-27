package com.example.loginandroid.service

import retrofit2.Call
import com.example.loginandroid.model.User
import retrofit2.http.Body
import retrofit2.http.POST

interface UserService {
    @POST("api/login")
    suspend fun postLogin(@Body user: User): Call<User>

    @POST("api/register")
    suspend fun postRegister(@Body user: User): Call<User>
}