package com.example.minimoneybox.api

import android.annotation.SuppressLint
import android.content.Context
import com.example.minimoneybox.dto.ErrorDto
import com.example.minimoneybox.dto.InvestorProductsDto
import com.example.minimoneybox.dto.LoginDto
import com.example.minimoneybox.dto.OneOffPaymentsDto
import com.google.gson.Gson
import com.google.gson.JsonObject
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException

object NetworkLayer {

//    private lateinit var sharedPreferences: SharedPreferences
//    private lateinit var editor: SharedPreferences.Editor

    lateinit var loginDto: LoginDto
    lateinit var investorProductsDto: InvestorProductsDto
    lateinit var oneOffPaymentsDto: OneOffPaymentsDto

    fun setUp(context: Context) {
//        sharedPreferences =
//            context.getSharedPreferences("com.example.minimoneybox", Context.MODE_PRIVATE)
//        editor = sharedPreferences.edit()
    }

    @SuppressLint("CheckResult")
    fun loginApiCall(email: String, userPassword: String, callback: () -> Unit) {
        val observable = RetrofitService.apiCall().doLogin(LoginPostData(email, userPassword))
        observable.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->
                loginDto = response
                callback.invoke()
//                editor.putString("TOKEN", loginDto.session.token).apply
            }, { e ->
                val error = e as HttpException
                println(error.response().errorBody()?.string())
            })
    }

    @SuppressLint("CheckResult")
    fun getInvestorProductsApiCall(callback: () -> Unit) {
        val observable =
            RetrofitService.apiCall().getInvestorProducts("Bearer " + loginDto.session.token)
        observable.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->
                investorProductsDto = response
                callback.invoke()
            }, { e ->
                val error = e as HttpException
                println(error.response().errorBody()?.string())
            })
    }

    @SuppressLint("CheckResult")
    fun oneOffPaymentsApiCall(amount: Float, productId: Int, callback: (moneybox: Float) -> Unit) {
        val observable =
            RetrofitService.apiCall().doOneOffPayment(
                "Bearer " + loginDto.session.token,
                OneOffPaymentsPostData(amount, productId)
            )
        observable.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->
                oneOffPaymentsDto = response
                callback.invoke(response.moneybox)
            }, { e ->
                val error = e as HttpException
                println(error.response().errorBody()?.string())
            })
    }

}