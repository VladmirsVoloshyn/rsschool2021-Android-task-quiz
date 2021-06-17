package com.rsschool.quiz.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import com.rsschool.quiz.data.Question
import com.rsschool.quiz.data.QuestionsRepository
import com.rsschool.quiz.databinding.FragmentQuizBinding


class QuizFragment : Fragment() {

    private var fragmentCommutator: FragmentCommutator? = null
    private var _binding: FragmentQuizBinding? = null
    private val binding get() = _binding!!
    private var chosenOption = 0
    private var isChosen = false
    private var currentIndex: Int? = 1
    private var prevAnswer: Int? = 0
    private lateinit var question: Question

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentQuizBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        currentIndex = arguments?.getInt(INDEX)
        prevAnswer = arguments?.getInt(ANSWER)

        question = QuestionsRepository.getData(currentIndex)

        binding.toolbar.title = TITLE + currentIndex.toString()

        when (currentIndex) {
            1 -> {
                binding.previousButton.isEnabled = false
                binding.toolbar.navigationIcon = null
                binding.nextButton.text = TEXT_BUTTON_NEXT
                binding.nextButton.setOnClickListener {
                    currentIndex?.let { it1 ->
                        fragmentCommutator?.hasNext(
                            it1,
                            chosenOption,
                            question.trueAnswer
                        )
                    }
                }
            }
            5 -> {
                binding.nextButton.text = TEXT_BUTTON_SUBMIT
                binding.toolbar.setNavigationOnClickListener {
                    currentIndex?.let { it1 ->
                        fragmentCommutator?.hasPrevious(
                            it1,
                            chosenOption,
                            question.trueAnswer
                        )
                    }
                }
                binding.nextButton.setOnClickListener {
                    fragmentCommutator?.hasSubmit(
                        currentIndex!!,
                        chosenOption,
                        QuestionsRepository.questions[currentIndex?.minus(1)!!].trueAnswer
                    )
                }
            }
            else -> {
                binding.nextButton.text = TEXT_BUTTON_NEXT
                binding.toolbar.setNavigationOnClickListener {
                    currentIndex?.let { it1 ->
                        fragmentCommutator?.hasPrevious(
                            it1,
                            chosenOption,
                            QuestionsRepository.questions[currentIndex?.minus(1)!!].trueAnswer
                        )
                    }
                }
                binding.nextButton.setOnClickListener {
                    currentIndex?.let { it1 ->
                        fragmentCommutator?.hasNext(
                            it1,
                            chosenOption,
                            question.trueAnswer
                        )
                    }
                }
            }
        }

        binding.previousButton.setOnClickListener {
            currentIndex?.let { it1 ->
                fragmentCommutator?.hasPrevious(
                    it1,
                    chosenOption,
                    question.trueAnswer
                )
            }
            setPreviousOption()
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

        binding.question.text = question.question
        binding.optionOne.text = question.firstAnswer
        binding.optionTwo.text = question.secondAnswer
        binding.optionThree.text = question.thirdAnswer
        binding.optionFour.text = question.fourthAnswer
        binding.optionFive.text = question.fifthAnswer

        requireActivity().onBackPressedDispatcher.addCallback(callback)

        setPreviousOption()
    }

    private val callback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            if (currentIndex != 1)
                currentIndex?.let {
                    fragmentCommutator?.hasPrevious(
                        it,
                        chosenOption,
                        question.trueAnswer
                    )
                }
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

    private fun setPreviousOption() {
        when (prevAnswer) {
            1 -> {
                binding.optionOne.isChecked = true
                binding.nextButton.isEnabled = true
                chosenOption = 1
            }
            2 -> {
                binding.optionTwo.isChecked = true
                binding.nextButton.isEnabled = true
                chosenOption = 2
            }
            3 -> {
                binding.optionThree.isChecked = true
                binding.nextButton.isEnabled = true
                chosenOption = 3
            }
            4 -> {
                binding.optionFour.isChecked = true
                binding.nextButton.isEnabled = true
                chosenOption = 4
            }
            5 -> {
                binding.optionFive.isChecked = true
                binding.nextButton.isEnabled = true
                chosenOption = 5
            }
        }
    }

    companion object {
        fun newInstance(prevAnswer: Int, index: Int): QuizFragment {
            val fragment = QuizFragment()
            val args = Bundle()
            args.putInt(INDEX, index)
            args.putInt(ANSWER, prevAnswer)
            fragment.arguments = args
            return fragment
        }

        const val TEXT_BUTTON_NEXT = "Next"
        const val TEXT_BUTTON_SUBMIT = "Submit"
        const val TITLE = "Question "
        const val ANSWER = "ANSWER"
        const val INDEX = "INDEX"
    }
}