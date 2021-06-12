package com.rsschool.quiz.ui

interface FragmentCommutator {
    fun hasNext(position : Int)
    fun hasPrevious (position: Int)
}