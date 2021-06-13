package com.rsschool.quiz.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.rsschool.quiz.AnswersController
import com.rsschool.quiz.databinding.FragmentResultBinding
import kotlin.concurrent.fixedRateTimer
import kotlin.system.exitProcess

class ResultFragment : Fragment() {

    private var fragmentCommutator: FragmentCommutator? = null
    private var _binding: FragmentResultBinding? = null
    private val binding get() = _binding!!
    private var answersController : AnswersController? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentResultBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        answersController = arguments?.getParcelable(PARCELABLE_TITLE)

        binding.exitButton.setOnClickListener {
            activity?.finish()
        }

        binding.backButton.setOnClickListener {
            val intent = Intent(context, MainActivity::class.java )
            startActivity(intent)
            activity?.finish()
        }

        binding.textView.text = answersController.toString()
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

    companion object{
        @JvmStatic
        fun newInstance(answersController: AnswersController) : ResultFragment {
            var fragment = ResultFragment()
            val args = Bundle()
            args.putParcelable(PARCELABLE_TITLE, answersController)
            fragment.arguments = args
            return fragment
        }

        const val PARCELABLE_TITLE = "Answer"
    }
}