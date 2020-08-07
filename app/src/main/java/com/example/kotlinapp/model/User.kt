package com.example.kotlinapp.model
import com.google.gson.annotations.SerializedName

data class User(@SerializedName ("name") val name : String,
                @SerializedName("avatar_url") val avatar_url : String) {}