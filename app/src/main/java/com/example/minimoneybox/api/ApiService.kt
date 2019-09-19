package com.example.minimoneybox.api

import com.example.minimoneybox.dto.InvestorProductsDto
import com.example.minimoneybox.dto.LoginDto
import com.example.minimoneybox.dto.OneOffPaymentsDto
import com.google.gson.annotations.SerializedName
import io.reactivex.Observable
import retrofit2.http.*

interface ApiService {

    @POST("users/login")
    fun doLogin(
        @Body loginPostData: LoginPostData
    ): Observable<LoginDto>

    @GET("investorproducts")
    fun getInvestorProducts(
        @Query("Authorization") authorizationKey: String
    ): Observable<InvestorProductsDto>

    @POST("oneoffpayments")
    fun doOneOffPayment(
        @Query("Authorization") authorizationKey: String,
        @Body oneOffPaymentsPostData: OneOffPaymentsPostData
    ): Observable<OneOffPaymentsDto>
}

data class LoginPostData(
    @SerializedName("Email") var userEmail: String,
    @SerializedName("Password") var userPassword: String
)

data class OneOffPaymentsPostData(
    @SerializedName("Amount") var amount: Float,
    @SerializedName("InvestorProductId") var productId: Int
)