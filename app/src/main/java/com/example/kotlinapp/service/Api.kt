package com.example.kotlinapp.service

import com.example.kotlinapp.model.User
import com.example.kotlinapp.model.UserRepos
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface Api {
    @GET("users/{userId}")
    suspend fun getUser(@Path("userId") user:String): Response<User>

    @GET("users/{userId}/repos")
    suspend fun getRepos(@Path("userId") user:String): Response<List<UserRepos>>

    companion object{
        operator fun invoke():Api{
            return Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(Api::class.java)
        }
    }
}