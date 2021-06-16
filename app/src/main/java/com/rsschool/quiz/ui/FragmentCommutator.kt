package com.rsschool.quiz.ui

import com.rsschool.quiz.AnswersController

interface FragmentCommutator {
    fun hasNext(index: Int, chosenOption: Int, trueAnswer: Int)
    fun hasPrevious(index: Int, chosenOption: Int, trueAnswer: Int)
    fun hasSubmit(index: Int, chosenOption: Int, trueAnswer: Int)
}