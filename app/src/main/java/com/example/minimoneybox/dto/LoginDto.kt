package com.example.minimoneybox.dto

import com.google.gson.annotations.SerializedName

data class LoginDto(
    @SerializedName("Session")
    val session: SessionDto,

    @SerializedName("User")
    val user: UserDto

)