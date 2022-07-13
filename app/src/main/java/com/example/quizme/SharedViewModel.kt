package com.example.quizme

import android.util.Log
import androidx.lifecycle.*
import com.example.data.quizme.Question
import com.example.data.quizme.QuestionResponse
import com.example.quizme.data.QuestionRepository
import com.example.quizme.data.States
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class SharedViewModel: ViewModel() {

    private val questionRepo: QuestionRepository = QuestionRepository( )
    private val _triviaLive = MutableLiveData<States<QuestionResponse?>>()
    val triviaLive: LiveData<States<QuestionResponse?>>
        get() = _triviaLive


    fun loadRemoteData(amount: Int = 10, category: Int = 21, difficulty: String = "medium", type: String = "multiple") {
        viewModelScope.launch {
             questionRepo.getQuestions(amount, category, difficulty, type).collect {
                 _triviaLive.postValue(it)
            }
        }
    }
/*
    fun getQuestion( counter: Int = 0 ): Question?
    {
        when( triviaLive.value )
        {
            is States.Success -> return triviaLive.
                value.
                toData().
                results[counter]

            else -> return null
        }
    }
    */
}
