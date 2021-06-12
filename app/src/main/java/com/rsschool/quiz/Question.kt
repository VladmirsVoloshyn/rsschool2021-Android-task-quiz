package com.rsschool.quiz

data class Question(
    val question: String, val firstAnswer: String, val secondAnswer: String,
    val thirdAnswer: String, val fourthAnswer: String, val fifthAnswer: String, val trueAnswer: Int
) {
}