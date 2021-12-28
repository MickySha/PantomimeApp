package com.micky.pantomim.ui.game

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GameViewModel: ViewModel() {

    lateinit var wordList: MutableList<String>

    private val _startFlag = MutableLiveData <Boolean> (false)
    val startFlag: LiveData<Boolean>
        get() = _startFlag

    private val _listFinish = MutableLiveData <Boolean> (false)
    val listFinish: LiveData<Boolean>
        get() = _listFinish

    private val _counter = MutableLiveData <Int> (0)
    val counter: LiveData<Int>
        get() = _counter

    private val _score = MutableLiveData<Int>(0)
    val score: LiveData<Int>
        get() = _score

    private val _myWord = MutableLiveData <String> ()
    val myWord: LiveData<String>
        get() = _myWord

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
        _score.value = _score.value?.plus(1)
    }

    fun nextWord() {
        if (counter.value!! <= (wordList.size) -1)
            _myWord.value = wordList[counter.value!!]

        if (counter.value == wordList.size)
            _listFinish.value = true
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

            _counter.value = _counter.value?.plus(1)
        }

        nextWord()
        stopTimerFun()
    }

    fun correctAnswerFun() {

        if (counter.value!! <= (wordList.size)  && !listFinish.value!!) {

            _counter.value = _counter.value?.plus(1)
            increaseScore()
        }

        nextWord()
        stopTimerFun()
    }
}