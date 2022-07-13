package com.example.quizme.data

import com.example.data.quizme.Question
import com.example.data.quizme.QuestionResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

// https://opentdb.com/api.php?amount=10&category=21&difficulty=medium&type=multiple

interface QuestionApi {

      //@GET("/api.php?amount={amount}&category={category}&difficulty={difficulty}&type={type}")
      @GET("/api.php")

      suspend fun getQuestions(
          @Query("amount") amount: Int = 10,
          @Query("category") category: Int = 21,
          @Query("difficulty") difficulty: String = "medium",
          @Query("type") type: String = "multiple"
            ): Response<QuestionResponse>
}

