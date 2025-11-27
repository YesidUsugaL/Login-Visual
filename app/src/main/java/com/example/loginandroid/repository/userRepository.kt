package com.example.loginandroid.repository

import com.example.loginandroid.model.User
import com.example.loginandroid.service.UserService

class UserRepository(private val api: UserService) {

    suspend fun login(user: User): User {
        return api.postLogin(user)
    }

    suspend fun register(user: User): User {
        return api.postRegister(user)
    }
}
