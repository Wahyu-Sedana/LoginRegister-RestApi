package com.example.restapi.api

import retrofit2.Call
import retrofit2.http.*

interface ApiServices {
    @POST("register")
    fun register(
            @Body regisRequest: RegisRequest
    ): Call<UserResponse>

    @POST("login")
    fun login(
            @Body userRequest: UserRequest
    ): Call<UserResponse>
}