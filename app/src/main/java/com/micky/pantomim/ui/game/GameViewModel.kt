package com.micky.pantomim.ui.game

import android.view.View
import androidx.lifecycle.ViewModel

class GameViewModel: ViewModel() {

    lateinit var wordList: MutableList<String>

    var startedFlag  = false
    var listFinished = false

    var counter = 0
    var score = 0

    var myWord: String = ""

    fun getWords() {

        wordList = mutableListOf(
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

    fun increaseScore() {
        ++score
    }

    fun nextWord() {
        if (counter <= (wordList.size) -1)
            myWord = wordList[counter]

        if (counter == wordList.size)
            listFinished = true
    }

    fun timerFun() {
        if (!startedFlag)
            startTimerFun()
        else
            stopTimerFun()
    }

    fun startTimerFun() {
        //TODO("Not yet implemented")
    }

    fun stopTimerFun() {
        //TODO("Not yet implemented")
    }

    fun addNewWordFun() {
        //TODO("Not yet implemented")
    }

    fun wrongAnswerFun() {

        if (counter <= (wordList.size) && !listFinished) {
            counter++
        }

        nextWord()
        stopTimerFun()
    }

    fun correctAnswerFun() {

        if (counter <= (wordList.size)  && !listFinished) {
            counter++
            increaseScore()
        }

        nextWord()
        stopTimerFun()
    }
}