package com.example.swipequiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.*
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_quiz.*

class MainActivity : AppCompatActivity() {
    private val quizes = arrayListOf<Quiz>()
    private val quizAdapter = QuizAdapter(quizes, {quiz: Quiz ->  quizItemClicked(quiz)})

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
    }

    private fun quizItemClicked(quiz: Quiz) {
                Snackbar.make(rvQuestions, quiz.answer.toString().capitalize(), Snackbar.LENGTH_SHORT)
            .show()
    }

    private fun initViews() {
        rvQuestions.layoutManager = LinearLayoutManager(this@MainActivity, RecyclerView.VERTICAL, false)
        rvQuestions.adapter = quizAdapter
        rvQuestions.addItemDecoration(
            DividerItemDecoration(
                this@MainActivity,
                DividerItemDecoration.VERTICAL
            )
        )

            // Populate the places list and notify the data set has changed.
        for (i in Quiz.QUESTION_NAMES.indices) {
            quizes.add(Quiz(Quiz.QUESTION_NAMES[i], Quiz.QUESTION_ANSWER[i]))
        }

        quizAdapter.notifyDataSetChanged()

        createItemTouchHelper().attachToRecyclerView(rvQuestions)


    }

    /**
     * Create a touch helper to recognize when a user swipes an item from a recycler view.
     * An ItemTouchHelper enables touch behavior (like swipe and move) on each ViewHolder,
     * and uses callbacks to signal when a user is performing these actions.
     */
    private fun createItemTouchHelper(): ItemTouchHelper {

        // Callback which is used to create the ItemTouch helper. Only enables left swipe.
        // Use ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) to also enable right swipe.
        val callback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val question = quizes.get(position)

                if (direction == ItemTouchHelper.LEFT) {
                    if (question.answer) {
                        Snackbar.make(viewHolder.itemView, "Correct!", Snackbar.LENGTH_SHORT)
                            .show()
                    } else {
                        Snackbar.make(viewHolder.itemView, "Your answer was wrong!", Snackbar.LENGTH_SHORT)
                            .show()
                    }
                } else {
                    if (!question.answer) {
                        Snackbar.make(viewHolder.itemView, "Correct!", Snackbar.LENGTH_SHORT)
                            .show()
                    } else {
                        Snackbar.make(viewHolder.itemView, "Your answer was wrong!", Snackbar.LENGTH_SHORT)
                            .show()
                    }
                }

                quizAdapter.notifyItemChanged(viewHolder.adapterPosition)
            }

            // Enables or Disables the ability to move items up and down.
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }
        }

        return ItemTouchHelper(callback)

    }

}
