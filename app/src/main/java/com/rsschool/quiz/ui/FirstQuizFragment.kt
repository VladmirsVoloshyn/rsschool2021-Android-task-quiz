package com.rsschool.quiz.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import com.rsschool.quiz.R
import com.rsschool.quiz.data.Questions
import com.rsschool.quiz.databinding.FirstFragmentQuizBinding

class FirstQuizFragment : Fragment() {

    private var fragmentCommutator: FragmentCommutator? = null
    private var _binding: FirstFragmentQuizBinding? = null
    private val binding get() = _binding!!
    private var chosenOption = 0
    private var isChosen = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FirstFragmentQuizBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        changeStatusBarColor(requireActivity(), STATUS_BAR_COLOR)

        binding.toolbar.title = TITLE
        binding.nextButton.setOnClickListener {
            fragmentCommutator?.hasNext(INDEX, chosenOption)
        }
        binding.previousButton.isEnabled = false

        if (!isChosen) {
            binding.nextButton.isEnabled = false
        }
        binding.optionOne.setOnClickListener {
            chooseOption(1)
        }
        binding.optionTwo.setOnClickListener {
            chooseOption(2)
        }
        binding.optionThree.setOnClickListener {
            chooseOption(3)
        }
        binding.optionFour.setOnClickListener {
            chooseOption(4)
        }
        binding.optionFive.setOnClickListener {
            chooseOption(5)
        }
        requireActivity().onBackPressedDispatcher.addCallback(callback)

        binding.question.text = Questions.questions[0].question
        binding.optionOne.text = Questions.questions[0].firstAnswer
        binding.optionTwo.text = Questions.questions[0].secondAnswer
        binding.optionThree.text = Questions.questions[0].thirdAnswer
        binding.optionFour.text = Questions.questions[0].fourthAnswer
        binding.optionFive.text = Questions.questions[0].fifthAnswer
    }

    private val callback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            fragmentCommutator?.hasPrevious(INDEX)
        }
    }

    private fun chooseOption(number: Int) {
        chosenOption = number
        isChosen = true
        binding.nextButton.isEnabled = true
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        fragmentCommutator = activity as FragmentCommutator
    }

    override fun onDestroyView() {
        _binding = null
        fragmentCommutator = null
        super.onDestroyView()

    }

    companion object {
        const val INDEX = 1
        const val TITLE = "Question 1"
        const val STATUS_BAR_COLOR = R.color.deep_orange_100_dark
    }
}