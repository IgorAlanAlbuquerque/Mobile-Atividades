package com.igor.authapp.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.igor.authapp.remote.AuthApi
import com.igor.authapp.remote.RetrofitClient
import com.igor.authapp.repository.AuthRepository

class AuthViewModelFactory(private val context: Context) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AuthViewModel::class.java)) {
            // 1. Cria a instância da API
            val api: AuthApi = RetrofitClient.instance.create(AuthApi::class.java)

            // 2. Cria a instância do Repositório, fornecendo a API e o Context
            val repository = AuthRepository(api, context.applicationContext)

            // 3. Cria o ViewModel com o Repositório
            @Suppress("UNCHECKED_CAST")
            return AuthViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}