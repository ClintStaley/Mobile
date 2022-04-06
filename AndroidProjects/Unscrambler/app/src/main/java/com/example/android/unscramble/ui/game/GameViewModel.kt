/*
 * Copyright (C) 2020 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.unscramble.ui.game

import android.util.Log
import android.widget.TextView
import androidx.annotation.MainThread
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.LiveData

/**
 * ViewModel containing the app data and methods to process the data
 */
class GameViewModel : ViewModel() {
    private val _score = object : MutableLiveData<Int>(0) {
        override @MainThread
        fun observe(owner: LifecycleOwner, observer: Observer<in Int>) {
            Log.e("GVM", "Hello " + observer.javaClass.canonicalName)
        }
    }

    val score: LiveData<Int>
        get() = _score

    private val _currentWordCount = MutableLiveData(0)
    val currentWordCount: LiveData<Int>
        get() = _currentWordCount

    private val _currentScrambledWord = MutableLiveData<String>()
    val currentScrambledWord: LiveData<String>
        get() = _currentScrambledWord

    private val _currentWord = MutableLiveData<String>("")
    val currentWord: LiveData<String>
        get() = _currentWord

    // List of words not yet used in the game
    private var wordsList : MutableList<String> = allWordsList.toMutableList()

    init {
        getNextWord()
    }

    /*
     * Updates currentWord and currentScrambledWord with the next word.
     */
    private fun getNextWord() {
        _currentWord.value = wordsList.random()
        val tempWord = (_currentWord.value as String).toCharArray()
        tempWord.shuffle()

        while (String(tempWord).equals(_currentWord.value, false)) {
            tempWord.shuffle()
        }
        Log.d("Unscramble", "currentWord= $currentWord")
        _currentScrambledWord.value = String(tempWord)
        _currentWordCount.value = _currentWordCount.value?.inc()
        wordsList.remove(_currentWord.value)
    }

    /*
     * Re-initializes the game data to restart the game.
     */
    fun reinitializeData() {
        _score.value = 0
        _currentWordCount.value = 0
        getNextWord()
    }

    /*
    * Increases the game score if the player’s word is correct.
    */
    private fun increaseScore() {
        _score.value = _score.value?.plus(SCORE_INCREASE)
    }

    /*
    * Returns true if the player word is correct.
    * Increases the score accordingly.
    */
    fun isUserWordCorrect(playerWord: String): Boolean {
        if (playerWord.equals(_currentWord.value, true)) {
            increaseScore()
            return true
        }
        return false
    }

    /*
    * Returns true if the current word count is less than MAX_NO_OF_WORDS
    */
    fun nextWord(): Boolean {
        return if (_currentWordCount.value!! < MAX_NO_OF_WORDS) {
            getNextWord()
            true
        } else false
    }
}

