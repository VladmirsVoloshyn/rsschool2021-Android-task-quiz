package com.rsschool.quiz.ui

import com.rsschool.quiz.AnswersController

interface FragmentCommutator {
    fun hasNext(index : Int, chosenOption : Int)
    fun hasPrevious (index: Int)
    fun hasSubmit (index: Int, chosenOption: Int )
}