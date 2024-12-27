package com.example.foodhub.data

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://192.168.41.57:8086")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }

    @Provides
    fun provideFoodHubApi(retrofit: Retrofit): FoodHubApi {
        return retrofit.create(FoodHubApi::class.java)
    }

}