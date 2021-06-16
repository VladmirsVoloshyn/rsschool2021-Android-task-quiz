package com.rsschool.quiz.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.rsschool.quiz.AnswersController
import com.rsschool.quiz.data.Questions
import com.rsschool.quiz.R
import com.rsschool.quiz.databinding.ActivityMainBinding
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity(), FragmentCommutator {


    private var currentIndex = 1
    private lateinit var binding: ActivityMainBinding
    private var resultFragment = ResultFragment()
    private var fragmentTransaction: FragmentTransaction = supportFragmentManager.beginTransaction()
    private val answersController = AnswersController()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val arrayOfQuestion = resources.getStringArray(R.array.questions)

        val arrayListAnswers: ArrayList<Array<String>> = ArrayList()

        val trueAnswers = resources.getIntArray(R.array.true_answers).toTypedArray()

        arrayListAnswers.add(resources.getStringArray(R.array.first_question_answers))
        arrayListAnswers.add(resources.getStringArray(R.array.second_question_answers))
        arrayListAnswers.add(resources.getStringArray(R.array.third_question_answers))
        arrayListAnswers.add(resources.getStringArray(R.array.fourth_question_answers))
        arrayListAnswers.add(resources.getStringArray(R.array.fifth_question_answers))

        Questions.init(arrayOfQuestion, arrayListAnswers, trueAnswers)

        setRandomTheme()

        fragmentTransaction.replace(
            binding.container.id,
            QuizFragment.newInstance(answersController.getRepliesAnswer(currentIndex), currentIndex)
        ).commit()
    }


    override fun hasNext(index: Int, chosenOption: Int, trueAnswer: Int) {
        currentIndex++
        if (currentIndex in 1..5) {
            fragmentTransaction(
                QuizFragment.newInstance(
                    answersController.getRepliesAnswer(currentIndex),
                    currentIndex
                )
            )
            answersController.registerAnswer(chosenOption, index, trueAnswer)
            setRandomTheme()
        }
    }

    override fun hasPrevious(index: Int, chosenOption: Int, trueAnswer: Int) {
        currentIndex--
        if (currentIndex in 1..5) {
            fragmentTransaction(
                QuizFragment.newInstance(
                    answersController.getRepliesAnswer(currentIndex),
                    currentIndex
                )
            )
            answersController.registerAnswer(chosenOption, index, trueAnswer)
            setRandomTheme()
        }
    }

    override fun hasSubmit(index: Int, chosenOption: Int, trueAnswer: Int) {
        answersController.registerAnswer(chosenOption, index, trueAnswer)
        resultFragment = ResultFragment.newInstance(answersController)
        fragmentTransaction(resultFragment)
        setRandomTheme()
    }

    private fun fragmentTransaction(fragment: Fragment) {
        fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(binding.container.id, fragment).commit()
    }

    private fun setRandomTheme() {
        when ((1..5).random()) {
            1 -> {
                setTheme(R.style.Theme_Quiz_First)
                changeStatusBarColor(this, (R.color.deep_orange_100_dark))
            }
            2 -> {
                setTheme(R.style.Theme_Quiz_Second)
                changeStatusBarColor(this, (R.color.yellow_100_dark))
            }
            3 -> {
                setTheme(R.style.Theme_Quiz_Third)
                changeStatusBarColor(this, (R.color.my_deep_blue))
            }
            4 -> {
                setTheme(R.style.Theme_Quiz_Fourth)
                changeStatusBarColor(this, (R.color.my_deep_purple))
            }
            5 -> {
                setTheme(R.style.Theme_Quiz_Fifth)
                changeStatusBarColor(this, (R.color.my_deep_green))
            }
        }
    }

}

