package com.example.android_lab3quiz

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.android_lab3quiz.databinding.FragmentQuestionDetailBinding
import quiz.Question
import quiz.QuizViewModel


class QuestionDetailFragment : Fragment() {

    private lateinit var binding: FragmentQuestionDetailBinding
    private lateinit var question: Question
    private lateinit var viewModel: QuizViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(requireActivity())[QuizViewModel::class.java]
        question = viewModel.selectedQuestion.value!!
        binding = FragmentQuestionDetailBinding.inflate(inflater, container, false);
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        binding.question.text = question.text
        binding.answerCorrect.text = question.correctAnswer
        binding.category.text = question.category
        binding.answer1.text = question.answers[0];
        binding.answer2.text = question.answers[1];
        binding.answer3.text = question.answers[2];
        binding.answer4.text = question.answers[3];

    }
}