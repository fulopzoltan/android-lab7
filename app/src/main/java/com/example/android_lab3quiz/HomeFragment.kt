package com.example.android_lab3quiz

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.android_lab3quiz.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    private lateinit var binding:FragmentHomeBinding;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater,container,false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonStartQuiz.setOnClickListener{
            (activity as MainActivity).replaceFragment(R.id.fragment_container_view,QuizStartFragment())
        }
        binding.buttonReadQuestions.setOnClickListener{
            (activity as MainActivity).replaceFragment(R.id.fragment_container_view,QuestionListFragment())
        }
        binding.buttonCreateQuestion.setOnClickListener{
            (activity as MainActivity).replaceFragment(R.id.fragment_container_view,QuestionNewFragment())
        }
    }

}