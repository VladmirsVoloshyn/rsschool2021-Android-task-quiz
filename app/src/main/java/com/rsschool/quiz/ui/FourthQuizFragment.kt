package com.rsschool.quiz.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import com.rsschool.quiz.databinding.FourthFragmentQuizBinding

class FourthQuizFragment : Fragment() {

    private var fragmentCommutator : FragmentCommutator? = null
    private var _binding: FourthFragmentQuizBinding? = null
    private val binding get() = _binding!!
    private var chosenOption = 0
    private var isChosen = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FourthFragmentQuizBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.question.text = "fourth fragment"

        binding.nextButton.setOnClickListener {
            fragmentCommutator?.hasNext(INDEX)
        }

        binding.previousButton.setOnClickListener {
            fragmentCommutator?.hasPrevious(INDEX)
        }

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

    }

    private val callback = object : OnBackPressedCallback(true){
        override fun handleOnBackPressed() {
            fragmentCommutator?.hasPrevious(INDEX)
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        fragmentCommutator = activity as FragmentCommutator

    }

    private fun chooseOption(number : Int){
        chosenOption = number
        isChosen = true
        binding.nextButton.isEnabled = true
    }

    override fun onDestroyView() {
        _binding  = null
        fragmentCommutator = null
        super.onDestroyView()

    }
    companion object{
        const val INDEX = 4
    }
}