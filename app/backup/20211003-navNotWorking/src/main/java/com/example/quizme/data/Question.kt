package com.example.data.quizme

data class Question(

    val category: String,
    val type: String,
    val difficulty: String,
    val text: String,
    val correct_answer: String,
    val incorrect_answers: List<String>
)
