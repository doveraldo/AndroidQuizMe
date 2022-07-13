package com.example.quizme

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.data.quizme.Question
import com.example.quizme.data.States
import com.example.quizme.databinding.FragmentTriviaBinding
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * A simple [Fragment] subclass.
 * Use the [triviaFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class triviaFragment : Fragment() {
    private lateinit var viewModel: SharedViewModel
    private var _binding: FragmentTriviaBinding? = null
    private val binding get() = _binding!!

    var questionCounter: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //_binding = inflater.inflate(R.layout.fragment_trivia, container, false)

        _binding = FragmentTriviaBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        defaultViewModelProvider( )
        Log.i("DOVER", "requesting API data")
        viewModel.loadRemoteData( )
        Log.i("DOVER", "processing API data")

        viewModel.triviaLive.observe(requireActivity(), {

            when(it)
            {
                is States.Success -> {
                    Log.i("DOVER", "States.Success")
                    showNextQuestion( )
                }
                is States.Loading -> Log.i("DOVER", "Loading questions")
                else -> Log.i("DOVER", "States Error")
            }

             it.toData()?.results?.forEach {
                Log.i("DOVER",  "Question: ${it.question} Answer: ${it.correct_answer} Incorrect: ${it.incorrect_answers}")

            }
        })

        Log.i("DOVER", "got questions")
    }

    private fun showNextQuestion() {
        val nextQuestion: Question? = viewModel.triviaLive.value?.toData( )?.results?.get(questionCounter)

        questionCounter++
        binding.questionTitleText.text = "Question $questionCounter of 10"

        if (nextQuestion != null) {
            Log.i("DOVER", "[$questionCounter] - Question is ${nextQuestion.question}")

            binding.questionText.text = nextQuestion.question
        }
    }

    private fun defaultViewModelProvider() {
        val modelProvider = SharedViewModelProvider()
        viewModel = ViewModelProvider(this, modelProvider).get(SharedViewModel::class.java)

    }
}