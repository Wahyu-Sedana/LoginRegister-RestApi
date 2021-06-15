package com.example.restapi.api

import com.google.gson.annotations.SerializedName

data class UserResponse(
        @SerializedName("data")
        var data: User? = null,

)

class User {
    @SerializedName("name")
    var name: String? = null

    @SerializedName("token")
    var token: String? = null

}
