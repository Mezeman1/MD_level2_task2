package com.example.swipequiz

import android.R
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class QuizAdapter(val quizes: List<Quiz>, val clickListener: (Quiz) -> Unit) :
    RecyclerView.Adapter<QuizAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        return ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.simple_list_item_1, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return quizes.size
    }

    override fun onBindViewHolder(holder: QuizAdapter.ViewHolder, position: Int) {
        holder.bind(quizes[position], clickListener)
    }

    lateinit var context: Context

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvQuiz: TextView = itemView.findViewById(android.R.id.text1)
        fun bind(
            quiz: Quiz,
            clickListener: (Quiz) -> Unit
        ) {
            tvQuiz.text = quiz.name
            tvQuiz.setOnClickListener { clickListener(quiz) }
        }
    }

}