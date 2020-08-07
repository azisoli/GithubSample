package com.example.kotlinapp.service

import com.example.kotlinapp.model.User
import com.example.kotlinapp.model.UserRepos
import retrofit2.Response

object Repository {

    suspend fun getUser(user:String): Response<User> {
        return Api().getUser(user)
    }

    suspend fun getUserRepos(user:String): Response<List<UserRepos>> {
        return Api().getRepos(user)
    }
}