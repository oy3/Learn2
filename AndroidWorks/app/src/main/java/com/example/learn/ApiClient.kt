package com.example.learn

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiClient {

    @GET("auth/login")
    fun reposForUser(@Path("email") user: String): Call<List<Repo>>

    @POST("auth/login")
    fun createAccount(@Body user: User): Call<User>



}