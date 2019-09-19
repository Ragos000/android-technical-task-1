package com.example.minimoneybox.dto

import com.google.gson.annotations.SerializedName

data class UserDto(
    @SerializedName("FirstName")
    val firstName: String,

    @SerializedName("LastName")
    val lastName: String
)