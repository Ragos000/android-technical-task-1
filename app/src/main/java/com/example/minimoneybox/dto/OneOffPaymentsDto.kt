package com.example.minimoneybox.dto

import com.google.gson.annotations.SerializedName

data class OneOffPaymentsDto(
    @SerializedName("Moneybox") val moneybox: Float
)