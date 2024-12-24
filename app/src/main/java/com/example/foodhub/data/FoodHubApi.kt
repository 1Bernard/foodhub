package com.example.foodhub.data

import retrofit2.http.GET

interface FoodHubApi {

    @GET("/food")
    suspend fun getFood(): List<String>
}