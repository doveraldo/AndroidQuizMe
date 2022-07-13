package com.example.quizme.data

import com.example.data.quizme.QuestionResponse
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response
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

    suspend fun getQuestions(amount: Int, category: Int, difficulty: String, type: String): Flow<States<QuestionResponse?>> {

        return flow {
            emit(States.Loading)
            val result: Response<QuestionResponse> = questionApi.getQuestions(amount, category, difficulty, type)
            try {
                if (result.isSuccessful)
                    emit(States.Success(result.body()))
                else
                    emit(States.Error(result.message()))
            } catch (e: Exception) {
                emit(States.Error(result.message()))
            }
        }

    }
}