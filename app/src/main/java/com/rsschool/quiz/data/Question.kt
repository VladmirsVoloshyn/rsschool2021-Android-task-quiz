package com.rsschool.quiz.data

data class Question(
    val question: String, val firstAnswer: String = "option one", val secondAnswer: String = "option two",
    val thirdAnswer: String = "option three", val fourthAnswer: String = "option four", val fifthAnswer: String= "option five", val trueAnswer: Int = 0
) {
}