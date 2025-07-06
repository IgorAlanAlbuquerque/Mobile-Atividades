package com.igor.authapp.remote

import com.igor.authapp.model.LoginRequest
import com.igor.authapp.model.TokenResponse
import com.igor.authapp.model.UserCreate
import com.igor.authapp.model.UserOut
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface AuthApi {
    @POST("register")
    suspend fun register(@Body user: UserCreate): Response<UserOut>

    @POST("login")
    suspend fun login(@Body request: LoginRequest):
            Response<TokenResponse>

    @GET("me")
    suspend fun me(@Header("Authorization" ) token: String):
            Response<UserOut>
}