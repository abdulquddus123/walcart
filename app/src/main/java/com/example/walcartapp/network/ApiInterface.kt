package com.example.walcartapp.network

import com.example.walcartapp.model.Category
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiInterface {
    @Headers("Content-Type: application/json")
    @POST("graphql")
    suspend fun getCategories(@Body body: String): Response<Category>
}