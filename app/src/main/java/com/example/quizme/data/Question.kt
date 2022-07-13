package com.example.data.quizme

data class Question (

    val category: String,
    val type: String,
    val difficulty: String,
    val question: String,
    val correct_answer: String,
    val incorrect_answers: List<String>
)

data class QuestionResponse (
    val response_code: String,
    val results: List<Question>
)