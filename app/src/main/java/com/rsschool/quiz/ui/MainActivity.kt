package com.rsschool.quiz.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.rsschool.quiz.AnswersController
import com.rsschool.quiz.data.Questions
import com.rsschool.quiz.R

class MainActivity : AppCompatActivity(), FragmentCommutator {

    private val firstQuizFragment = FirstQuizFragment()
    private val secondQuizFragment = SecondQuizFragment()
    private val thirdQuizFragment = ThirdQuizFragment()
    private val fourthQuizFragment = FourthQuizFragment()
    private val fifthQuizFragment = FifthQuizFragment()
    private var resultFragment = ResultFragment()
    private var fragmentTransaction: FragmentTransaction = supportFragmentManager.beginTransaction()
    private val answersController = AnswersController()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        fragmentTransaction.replace(R.id.container, firstQuizFragment).commit()

        val arrayOfQuestion = resources.getStringArray(R.array.questions)

        val arrayListAnswers: ArrayList<Array<String>> = ArrayList()

        arrayListAnswers.add(resources.getStringArray(R.array.first_question_answers))
        arrayListAnswers.add(resources.getStringArray(R.array.second_question_answers))
        arrayListAnswers.add(resources.getStringArray(R.array.third_question_answers))
        arrayListAnswers.add(resources.getStringArray(R.array.fourth_question_answers))
        arrayListAnswers.add(resources.getStringArray(R.array.fifth_question_answers))

        Questions.init(arrayOfQuestion, arrayListAnswers)

    }


    override fun hasNext(index: Int, chosenOption : Int) {
        when (index) {
            1 -> fragmentTransaction(secondQuizFragment)
            2 -> fragmentTransaction(thirdQuizFragment)
            3 -> fragmentTransaction(fourthQuizFragment)
            4 -> fragmentTransaction(fifthQuizFragment)
        }
        answersController.registerAnswer(chosenOption, index)
    }

    override fun hasPrevious(index: Int) {
        when (index) {
            2 -> fragmentTransaction(firstQuizFragment)
            3 -> fragmentTransaction(secondQuizFragment)
            4 -> fragmentTransaction(thirdQuizFragment)
            5 -> fragmentTransaction(fourthQuizFragment)
        }
    }

    override fun hasSubmit(index: Int, chosenOption: Int) {
        answersController.registerAnswer(chosenOption, index)
        resultFragment = ResultFragment.newInstance(answersController)
        fragmentTransaction(resultFragment)
    }

    private fun fragmentTransaction(fragment: Fragment) {
        fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.container, fragment).commit()
    }

}

