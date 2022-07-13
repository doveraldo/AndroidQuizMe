package com.example.quizme.data

import com.example.data.quizme.Question
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

const val BASE_ENDPOINT_URL = "https://opentdb.com/"

class QuestionRepository {

    private val retrofit: Retrofit by lazy {
        val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory( )).build()

        Retrofit.Builder()
            .baseUrl(BASE_ENDPOINT_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    private val questionApi: QuestionApi by lazy {
        retrofit.create(QuestionApi::class.java)
    }

    fun getQuestions( ): List<Question> {
        val response = questionApi.getQuestions( )
        return if( response.isSuccessful) response.body() ?: emptyList()
        else emptyList( )
    }
}