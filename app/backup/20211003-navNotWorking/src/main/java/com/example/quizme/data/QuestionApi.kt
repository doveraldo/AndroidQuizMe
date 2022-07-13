package com.example.quizme.data

import com.example.data.quizme.Question
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

// https://opentdb.com/api.php?amount=10&category=21&difficulty=medium&type=multiple

interface QuestionApi {

      @GET("/api.php?amount={amount}&category={category}&difficulty={difficulty}&type={type}")
      fun getQuestions(
                  @Path("amount") amount: Int = 10,
                  @Path("category") category: Int = 21,
                  @Path("difficulty") difficulty: String = "medium",
                  @Path("type") type: String = "multiple"
            ): Response<List<Question>>
}

