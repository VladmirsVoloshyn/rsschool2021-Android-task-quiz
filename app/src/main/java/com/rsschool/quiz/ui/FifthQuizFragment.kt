package com.rsschool.quiz.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import com.rsschool.quiz.databinding.FifthFragmentQuizBinding
import com.rsschool.quiz.databinding.FirstFragmentQuizBinding

class FifthQuizFragment : Fragment() {

    private  var fragmentCommutator : FragmentCommutator? = null
    private var _binding: FifthFragmentQuizBinding? = null
    private val binding get() = _binding!!
    private var chosenOption = 0
    private var isChosen = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FifthFragmentQuizBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.question.text = "fifth fragment"


        binding.submitButton.setOnClickListener {
            val intent = Intent(activity, ResultActivity::class.java)
            startActivity(intent)
        }

        binding.previousButton.setOnClickListener {
            fragmentCommutator?.hasPrevious(INDEX)
        }

        if (!isChosen) {
            binding.submitButton.isEnabled = false
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

    private fun chooseOption(number : Int){
        chosenOption = number
        isChosen = true
        binding.submitButton.isEnabled = true
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        fragmentCommutator = activity as FragmentCommutator
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding  = null
        fragmentCommutator = null
    }

    companion object{
        const val INDEX = 5
    }
}