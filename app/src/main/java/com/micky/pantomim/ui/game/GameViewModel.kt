package com.micky.pantomim.ui.game

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GameViewModel: ViewModel() {

    lateinit var wordList: MutableList<String>

    val startFlag  = MutableLiveData <Boolean> (false)
    val listFinish = MutableLiveData <Boolean> (false)

    val counter    = MutableLiveData <Int> (0)
    val score      = MutableLiveData <Int> (0)

    val myWord     = MutableLiveData <String> ()

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

    private fun increaseScore() {
        score.value = score.value?.plus(1)
    }

    fun nextWord() {
        if (counter.value!! <= (wordList.size) -1)
            myWord.value = wordList[counter.value!!]

        if (counter.value == wordList.size)
            listFinish.value = true
    }

    fun timerFun() {
        if (!startFlag.value!!)
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

        if (counter.value!! <= (wordList.size) && !listFinish.value!!) {

            counter.value = counter.value?.plus(1)
        }

        nextWord()
        stopTimerFun()
    }

    fun correctAnswerFun() {

        if (counter.value!! <= (wordList.size)  && !listFinish.value!!) {

            counter.value = counter.value?.plus(1)
            increaseScore()
        }

        nextWord()
        stopTimerFun()
    }
}