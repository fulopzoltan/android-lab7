package com.example.android_lab3quiz

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android_lab3quiz.databinding.FragmentQuestionListBinding
import quiz.QuizViewModel


class QuestionListFragment : Fragment() {

    private lateinit var binding: FragmentQuestionListBinding
    private lateinit var viewModel: QuizViewModel
    private lateinit var recyclerView: RecyclerView


    private val onClickListener = object : OnQuestionClickedListener{
        override fun onDeleteClicked(pos: Int) {
            viewModel.deleteQuestion(pos)
        }

        override fun onDetailsClicked(pos: Int) {
            viewModel.selectQuestion(pos)
            (activity as MainActivity).replaceFragment(R.id.fragment_container_view,QuestionDetailFragment())
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentQuestionListBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(requireActivity()).get(QuizViewModel::class.java)
        recyclerView = binding.recyclerView
        return binding.root;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = QuestionListAdapter(viewModel.questions.value!!,onClickListener)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
    }
}


interface OnQuestionClickedListener{
    fun onDeleteClicked(pos:Int)
    fun onDetailsClicked(pos:Int)
}