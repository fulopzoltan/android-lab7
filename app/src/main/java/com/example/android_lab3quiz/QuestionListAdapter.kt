package com.example.android_lab3quiz

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import quiz.Question

class QuestionListAdapter(private val questionList: MutableList<Question>, private val onClick: OnQuestionClickedListener) :
    RecyclerView.Adapter<QuestionListAdapter.ViewHolder>(){
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val questionText: TextView = view.findViewById(R.id.question)
        val answerText: TextView = view.findViewById(R.id.answer)
        val buttonDetails: Button = view.findViewById(R.id.button_details)
        val buttonDelete: Button = view.findViewById(R.id.button_delete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.question_card, parent, false)

        val holder = ViewHolder(view)

        holder.buttonDelete.setOnClickListener{
            onClick.onDeleteClicked(holder.adapterPosition)
            notifyItemRemoved(holder.adapterPosition)
        }
        holder.buttonDetails.setOnClickListener{
            onClick.onDetailsClicked(holder.adapterPosition)

        }
        return holder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentQuestion = questionList[position]
        holder.questionText.text = currentQuestion.text
        holder.answerText.text = currentQuestion.correctAnswer
    }

    override fun getItemCount(): Int = questionList.size


}

