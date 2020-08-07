package com.example.kotlinapp.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlinapp.model.User
import com.example.kotlinapp.model.UserRepos
import com.example.kotlinapp.service.Repository
import com.example.kotlinapp.utils.ResponseHelper
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

class UserViewModel : ViewModel() {

    private val user = MutableLiveData<ResponseHelper<User>>()
    private val repos = MutableLiveData<ResponseHelper<List<UserRepos>>>()
    private val buttonsEnabled = MutableLiveData<Boolean>()

    private val userExceptionHandler = CoroutineExceptionHandler{context, exception ->
        user.value = ResponseHelper.error("Something Went Wrong", null)
    }
    private val repoExceptionHandler = CoroutineExceptionHandler{context, exception ->
        repos.value = ResponseHelper.error("Something Went Wrong", null)
    }

    init {
        buttonsEnabled.value = true
    }

    fun getUser(userId: String): LiveData<ResponseHelper<User>> {
        callUser(userId)
        return user
    }

    fun getRepos(userId: String): LiveData<ResponseHelper<List<UserRepos>>> {
        callRepos(userId)
        return repos
    }

    fun callUser(userId: String) {
        if (buttonsEnabled.value == true) {
            viewModelScope.launch (userExceptionHandler){
                buttonsEnabled.value = false
                try {
                    val response = Repository.getUser(userId)
                    when (response.isSuccessful) {
                        true -> response.body()?.let { user.value = ResponseHelper.success(response.body()) }
                        false -> user.value = ResponseHelper.error("it has failed!", null)
                    }
                } finally {
                    buttonsEnabled.value = true
                }
            }
        }
    }

    fun callRepos(userId: String) {
        viewModelScope.launch(repoExceptionHandler) {
            val response = Repository.getUserRepos(userId)
            when (response.isSuccessful) {
                true -> response.body()?.let{repos.value = ResponseHelper.success(response.body())}
                false -> repos.value = ResponseHelper.error("it has failed!", null)
            }
        }
    }
}
