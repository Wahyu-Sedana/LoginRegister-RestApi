package com.example.restapi.api

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Retrofit {
    fun getRetroClientInstance(): Retrofit {
        val gson = GsonBuilder().setLenient().create()
        return Retrofit.Builder()
                .baseUrl("https://simple-api-android.000webhostapp.com/api/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
    }
}