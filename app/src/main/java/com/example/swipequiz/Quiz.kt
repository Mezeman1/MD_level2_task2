package com.example.swipequiz
    data class Quiz(
        var name: String,
        var answer: Boolean
    ) {
        companion object {
            val QUESTION_NAMES = arrayOf(
                "A val and var are the same",
                "Mobile development grants 12 EC",
                "A Unit in kotlin corresponds a void in Java"
            )
            val QUESTION_ANSWER = arrayOf(
                true,
                false,
                false

            )
        }
}