package com.example.kotlinapp.utils

data class ResponseHelper<out T>(val status: Status, val data: T?, val message: String?) {

    companion object {

        fun <T> success(data: T?): ResponseHelper<T> {
            return ResponseHelper(Status.SUCCESS, data, null)
        }

        fun <T> error(msg: String, data: T?): ResponseHelper<T> {
            return ResponseHelper(Status.ERROR, data, msg)
        }
    }

}