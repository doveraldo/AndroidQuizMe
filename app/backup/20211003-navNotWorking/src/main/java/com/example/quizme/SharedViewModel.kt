package com.example.quizme

import android.util.Log
import androidx.lifecycle.*
import com.example.data.quizme.Question
import com.example.quizme.data.QuestionRepository

class SharedViewModel: ViewModel() {

    val questionRepo: QuestionRepository = QuestionRepository( )

    fun getQuestions( )
    {
        questionRepo.getQuestions( )
    }

}
