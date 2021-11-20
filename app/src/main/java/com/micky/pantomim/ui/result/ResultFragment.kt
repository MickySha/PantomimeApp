package com.micky.pantomim.ui.result

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.micky.pantomim.R
import com.micky.pantomim.databinding.FragmentResultBinding


/**
 * A simple [Fragment] subclass.
 * Use the [ResultFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ResultFragment : Fragment() {

    lateinit var binding: FragmentResultBinding

    var finalScore = 0
    var finalCounter = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentResultBinding.inflate(inflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        onClickFun()
        getArgumentsFun()
        showFinalScore()
    }

    private fun onClickFun() {
        binding.btnPlayAgain.setOnClickListener(View.OnClickListener {
            openStartFragment()
        })
    }

    private fun getArgumentsFun() {
        finalCounter = ResultFragmentArgs.fromBundle(requireArguments()).counter
        finalScore   = ResultFragmentArgs.fromBundle(requireArguments()).score
    }

    private fun showFinalScore() {
        binding.tvFinalScore.text = "$finalScore from $finalCounter"
    }

    private fun openStartFragment() {
        findNavController().navigateUp()
    }
}