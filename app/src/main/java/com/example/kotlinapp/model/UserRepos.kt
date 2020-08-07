package com.example.kotlinapp.model
import com.google.gson.annotations.SerializedName

data class UserRepos(@SerializedName("name")val name : String,
                     @SerializedName ("description")val description : String,
                     @SerializedName ("updated_at")val updated_at:String,
                     @SerializedName ("stargazers_count")val stargazers_count : Int,
                     @SerializedName ("forks") val forks : Int) {
}