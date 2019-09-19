package com.example.minimoneybox.dto

import com.google.gson.annotations.SerializedName

data class ErrorDto(
    @SerializedName("Name")
    val errorName: String,
    @SerializedName("Message")
    val errorMessage: String
)