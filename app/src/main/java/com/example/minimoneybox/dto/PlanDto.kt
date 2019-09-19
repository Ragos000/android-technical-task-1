package com.example.minimoneybox.dto

import com.google.gson.annotations.SerializedName

data class PlanDto(
    @SerializedName("Id")
    val id: Int,

    @SerializedName("PlanValue")
    val planValue: Float,

    @SerializedName("Moneybox")
    val moneyBox: Float,

    @SerializedName("Product")
    val product: ProductDto
)