package com.example.restapi.api

import com.google.gson.annotations.SerializedName

data class RegisRequest(
        @SerializedName("name")
        var name: String? = null,
        @SerializedName("email")
        var email: String? = null,
        @SerializedName("password")
        var password: String? = null,
        @SerializedName("c_password")
        var c_password: String? = null


)
