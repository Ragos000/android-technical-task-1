package com.example.minimoneybox.api

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory

object RetrofitService {
    private val TAG = "--RetrofitService"

    fun apiCall() = Retrofit.Builder()
        .baseUrl("https://api-test01.moneyboxapp.com")
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(RetrofitWorker.gsonConverter)
        .client(RetrofitWorker.client)
        .build()
        .create(ApiService::class.java)!!
}