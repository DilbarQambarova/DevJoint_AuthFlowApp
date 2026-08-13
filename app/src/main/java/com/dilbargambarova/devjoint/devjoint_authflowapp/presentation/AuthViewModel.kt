package com.dilbargambarova.devjoint.devjoint_authflowapp.presentation

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import androidx.core.content.edit
import kotlin.time.Duration.Companion.milliseconds

class AuthViewModel(application: Application) : AndroidViewModel(application) {

    private val sharedPreferences = application.getSharedPreferences(
        "auth_prefs", Context.MODE_PRIVATE)

    private val _authState = MutableStateFlow<AuthState>(AuthState.Idle)
    val authState: StateFlow<AuthState> = _authState.asStateFlow()

    init {
        checkSession()
    }

    private fun checkSession() {
        val savedToken = sharedPreferences.getString("jwt_token", null)
        if (savedToken != null) {
            _authState.value = AuthState.Success(savedToken)
        }
    }

    fun login(email: String, password: String) {
        viewModelScope.launch {
            _authState.value = AuthState.Loading

            delay(1500.milliseconds)

            if (email == "network@test.com") {
                _authState.value = AuthState.Error("Şəbəkə xətası: İnternet bağlantınızı yoxlayın!")
            } else if (password != "123456") {
                _authState.value = AuthState.Error("Yanlış şifrə və ya e-poçt daxil etdiniz!")
            } else {
                val token = "mock_jwt_token_777"
                sharedPreferences.edit { putString("jwt_token", token) }
                _authState.value = AuthState.Success(token)
            }
        }
    }
    fun logout() {
        sharedPreferences.edit { remove("jwt_token") }
        _authState.value = AuthState.Idle
    }

    fun resetState() {
        if (sharedPreferences.getString("jwt_token", null) == null) {
            _authState.value = AuthState.Idle
        }
    }
}