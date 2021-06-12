package com.rsschool.quiz.ui

import android.os.Bundle
import android.text.TextUtils.replace
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.rsschool.quiz.R
import com.rsschool.quiz.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), FragmentCommutator {

    private val firstQuizFragment = FirstQuizFragment()
    private val secondQuizFragment= SecondQuizFragment()
    private val thirdQuizFragment = ThirdQuizFragment()
    private val fourthQuizFragment = FourthQuizFragment()
    private val fifthQuizFragment = FifthQuizFragment()
    private var fragmentTransaction: FragmentTransaction = supportFragmentManager.beginTransaction()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        fragmentTransaction.replace(R.id.container, firstQuizFragment).commit()
    }

    override fun hasNext(position: Int) {
        when (position) {
            1 -> fragmentTransaction(secondQuizFragment)
            2 -> fragmentTransaction(thirdQuizFragment)
            3 -> fragmentTransaction(fourthQuizFragment)
            4 -> fragmentTransaction(fifthQuizFragment)
        }
    }

    override fun hasPrevious(position: Int) {
        when (position) {
            2 -> fragmentTransaction(firstQuizFragment)
            3 -> fragmentTransaction(secondQuizFragment)
            4 -> fragmentTransaction(thirdQuizFragment)
            5 -> fragmentTransaction(fourthQuizFragment)
        }
    }

    private fun fragmentTransaction(fragment : Fragment){
        fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.container, fragment).commit()
    }
}