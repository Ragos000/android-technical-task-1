package com.example.minimoneybox.dto

import com.google.gson.annotations.SerializedName

data class ProductDto(
    @SerializedName("FriendlyName")
    val friendlyName: String
)