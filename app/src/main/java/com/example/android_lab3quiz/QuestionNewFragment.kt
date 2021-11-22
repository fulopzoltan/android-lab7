package com.example.android_lab3quiz

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.android_lab3quiz.databinding.FragmentQuestionDetailBinding
import com.example.android_lab3quiz.databinding.FragmentQuestionNewBinding
import quiz.Question
import quiz.QuizViewModel

class QuestionNewFragment : Fragment() {


    private lateinit var viewModel: QuizViewModel
    lateinit var binding: FragmentQuestionNewBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(requireActivity())[QuizViewModel::class.java]
        binding = FragmentQuestionNewBinding.inflate(inflater, container, false);
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.addButton.setOnClickListener {
            val questionText = binding.questionText.text.toString()
            val correct = binding.answerCorrect.text.toString()
            val wrong1 = binding.answerWrong.text.toString()
            val wrong2 = binding.answerWrong2.text.toString()
            val wrong3 = binding.answerWrong3.text.toString()

            if (questionText.isNullOrEmpty() || correct.isNullOrEmpty() || wrong1.isNullOrEmpty() || wrong2.isNullOrEmpty() || wrong3.isNullOrEmpty()) return@setOnClickListener

            val question = Question(
                questionText,
                answers = mutableListOf(correct, wrong1, wrong2, wrong3),
                correctAnswer = correct
            )
            viewModel.addQuestion(question)
            (requireActivity() as MainActivity).replaceFragment(R.id.fragment_container_view,QuestionListFragment())
        }
    }

}