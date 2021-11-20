package com.micky.pantomim.ui.game

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.micky.pantomim.databinding.FragmentGameBinding


/**
 * A simple [Fragment] subclass.
 * Use the [GameFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class GameFragment : Fragment() {

    lateinit var binding: FragmentGameBinding
    var myWord: String = ""
    lateinit var wordList: MutableList<String>
    var counter =0
    var score = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentGameBinding.inflate(inflater)

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

        if (counter <= (wordList.size) )
            showScore()

        nextWord()
        stopTimerFun()
    }

    private fun correctAnswerFun() {
        stopTimerFun()

        if (counter <= (wordList.size) ) {
            increaseScore()

            if(counter == wordList.size)
                counter ++
        }
        nextWord()
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
            counter++
        }

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
}