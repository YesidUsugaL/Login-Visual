package com.example.loginandroid

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.loginandroid.model.User
import com.example.loginandroid.repository.UserRepository
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.example.loginandroid.service.UserService
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

class AuthViewModel : ViewModel() {

    private val api = Retrofit.Builder()
        .baseUrl("http://10.1.113.74:8080/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(UserService::class.java)

    private val repo = UserRepository(api)

    var loginResult by mutableStateOf<User?>(null)
        private set

    var errorMessage by mutableStateOf<String?>(null)
        private set

    fun login(name: String, password: String) {
        viewModelScope.launch {
            try {
                val user = User(null, name, password)
                loginResult = repo.login(user)
            } catch (e: Exception) {
                errorMessage = e.message
            }
        }
    }

    fun register(name: String, password: String) {
        viewModelScope.launch {
            try {
                val user = User(null, name, password)
                loginResult = repo.register(user)
            } catch (e: Exception) {
                errorMessage = e.message
            }
        }
    }
}
