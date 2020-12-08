package com.example.adda247assignment.model

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface RestApiInterface {
    @GET("users")
    fun fetchUser(
        @Query("page") page: Int
    ): Call<UserResponse?>
}