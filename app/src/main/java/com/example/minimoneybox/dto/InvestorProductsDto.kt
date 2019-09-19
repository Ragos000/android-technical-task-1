package com.example.minimoneybox.dto

import com.google.gson.annotations.SerializedName

data class InvestorProductsDto(
    @SerializedName("TotalPlanValue")
    val totalPlanValue: Float,

    @SerializedName("ProductResponses")
    val plans: List<PlanDto>
)