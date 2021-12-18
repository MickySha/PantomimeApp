package com.micky.pantomim.ui.game

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.micky.pantomim.databinding.FragmentGameBinding


/**
 * A simple [Fragment] subclass.
 * Use the [GameFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class GameFragment : Fragment() {

    lateinit var binding: FragmentGameBinding

    lateinit var viewModel: GameViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentGameBinding.inflate(inflater)

        viewModel = ViewModelProvider(this).get(GameViewModel::class.java)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getWords ()

        binding.tvTimer.text = viewModel.wordList.size.toString()

        onClickFun()
        showScore()

        viewModel.nextWord()
        showPantomimeWord()
    }

    private fun onClickFun() {

        binding.btnAddWord.setOnClickListener(View.OnClickListener {
            viewModel.addNewWordFun()
        })

        binding.btnStartTimer.setOnClickListener(View.OnClickListener {
            viewModel.timerFun()
        })

        binding.btnCorrect.setOnClickListener(View.OnClickListener {
            viewModel.correctAnswerFun()

            showPantomimeWord()
            showScore()

            finishBtnVisibility()
        })

        binding.btnWrong.setOnClickListener(View.OnClickListener {
            viewModel.wrongAnswerFun()

            showPantomimeWord()
            showScore()

            finishBtnVisibility()
        })

        binding.btnFinish.setOnClickListener(View.OnClickListener {
            openResultFragment ()
        })
    }


    private fun showScore() {
        binding.tvScore.text = "Points: ${viewModel.score.toString()}/${viewModel.counter}"
    }

    private fun finishBtnVisibility() {

        if (viewModel.listFinished)
            binding.btnFinish.visibility = View.VISIBLE
    }

    private fun showPantomimeWord() {
        binding.tvPantomimeWord.text = viewModel.myWord
    }

    private fun openResultFragment() {
        findNavController().navigate(
            GameFragmentDirections.actionGameFragmentToResultFragment(viewModel.score, viewModel.counter))
    }


//    override fun onSaveInstanceState(outState: Bundle) {
//        super.onSaveInstanceState(outState)
//
//        outState.putInt("SCORE", score)
//        outState.putInt("COUNTER", counter)
//    }
//
//    override fun onViewStateRestored(savedInstanceState: Bundle?) {
//        super.onViewStateRestored(savedInstanceState)
//
//        score   = savedInstanceState?.getInt("SCORE", 0) ?: 0
//        counter = savedInstanceState?.getInt("COUNTER", 0) ?: 0
//
//        showScore()
//        nextWord()
//    }


} // end of Class