package com.micky.pantomim.ui.game

import android.os.CountDownTimer
import android.text.format.DateUtils
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GameViewModel: ViewModel() {

    lateinit var wordList: MutableList<String>

    private val _startTimerFlag = MutableLiveData <Boolean> (false)
    val startTimerFlag: LiveData<Boolean>
        get() = _startTimerFlag

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

    private val _currentTime = MutableLiveData <Long> (10)
    val currentTime: LiveData<Long>
        get() = _currentTime

    private val _formatCurrentTime = MutableLiveData <String> ()
    val formatCurrentTime: LiveData<String>
        get() = _formatCurrentTime

    private lateinit var timer: CountDownTimer

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

        if (!startTimerFlag.value!!) {
            startTimerFun()
        }

        else {
            stopTimerFun()
        }
    }

    private fun startTimerFun() {

        _startTimerFlag.value = true

        timer = object: CountDownTimer (10_000, 1000){

            override fun onTick(time: Long) {

                _currentTime.value = time

                formatTime(time)
            }

            override fun onFinish() {
                stopTimerFun()
            }

        }

        timer.start()
    }

    private fun formatTime (timer: Long) {

        _formatCurrentTime.value = DateUtils.formatElapsedTime(timer/1000)
    }

    fun stopTimerFun() {

        _startTimerFlag.value = false
        timer.cancel()
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