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
    var myWord: String = ""

    lateinit var viewModel: GameViewModel

    lateinit var wordList: MutableList<String>

    var counter =0
    var score = 0

    var listFinished: Boolean = false

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

        wordList= getWords ()
        binding.tvTimer.text = wordList.size.toString()

        onClickFun()
        showScore()
        nextWord()
    }

    private fun onClickFun() {

        binding.btnAddWord.setOnClickListener(View.OnClickListener {
            addNewWordFun()
        })
        binding.btnStartTimer.setOnClickListener(View.OnClickListener {
            startTimerFun()
        })
        binding.btnCorrect.setOnClickListener(View.OnClickListener {
            correctAnswerFun()
        })
        binding.btnWrong.setOnClickListener(View.OnClickListener {
            wrongAnswerFun()
        })
        binding.btnFinish.setOnClickListener(View.OnClickListener {
            openResultFragment ()
        })
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putInt("SCORE", score)
        outState.putInt("COUNTER", counter)
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)

        score   = savedInstanceState?.getInt("SCORE", 0) ?: 0
        counter = savedInstanceState?.getInt("COUNTER", 0) ?: 0

        showScore()
        nextWord()
    }

    private fun startTimerFun() {
        //TODO("Not yet implemented")
    }

    private fun stopTimerFun() {
        //TODO("Not yet implemented")
    }

    private fun addNewWordFun() {
       //TODO("Not yet implemented")
    }

    private fun wrongAnswerFun() {

        if (counter <= (wordList.size) && !listFinished) {
            counter++
            showScore()
        }

        nextWord()
        stopTimerFun()

        if (counter == wordList.size) {
            listFinished = true
            binding.btnFinish.visibility = View.VISIBLE
        }
    }

    private fun correctAnswerFun() {

        if (counter <= (wordList.size)  && !listFinished) {
            counter++
            increaseScore()
        }

        nextWord()
        stopTimerFun()

        if (counter == wordList.size) {
            listFinished = true
            binding.btnFinish.visibility = View.VISIBLE
        }
    }

    private fun increaseScore() {
        ++score
        showScore()
    }

    private fun showScore() {
        binding.tvScore.text = "Points: ${score.toString()}/${counter}"
    }

    private fun nextWord() {
        if (counter <= (wordList.size) -1) {

            myWord = wordList[counter]
        }
        showPantomimeWord()
    }

    private fun showPantomimeWord() {
        binding.tvPantomimeWord.text = myWord
    }

    private fun getWords(): MutableList<String> {

        return mutableListOf(
            "Lake",
            "Sea",
            "Ocean",
            "Nature",
            "Assistant",
            "Access",
            "Delivery",
            "Grammar",
            "Luxury",
            "Fake",
            "Pizza Tower",
            "Plankton",
            "Timer",
            "Sticker",
            "Comment",
            "Confirm",
            "Duel",
            "Farm",
            "Tractor",
            "Track",
            "Pace",
            "Clockwise",
            "Microbiology"
        )
    }

    private fun openResultFragment() {
        findNavController().navigate(
            GameFragmentDirections.actionGameFragmentToResultFragment(score, counter))
    }
}