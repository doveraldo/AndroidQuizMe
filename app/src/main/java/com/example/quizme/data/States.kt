package com.example.quizme.data

sealed class States<out T>{
    data class Success<T>(val data:T) : States<T>()
    data class Error(val message:String) : States<Nothing>()
    object Loading : States<Nothing>()

    fun toData() : T?= if (this is Success) data else null
}