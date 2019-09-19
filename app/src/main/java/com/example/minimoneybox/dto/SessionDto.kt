package com.example.minimoneybox.dto

import com.google.gson.annotations.SerializedName

data class SessionDto(

    @SerializedName("BearerToken")
    val token: String = "",

    @SerializedName("ExternalSessionId")
    val externalSessionId: String = "",

    @SerializedName("SessionExternalId")
    val sessionExternalId: String = "",

    @SerializedName("ExpiryInSeconds")
    val expiryInSeconds: String = ""
)