package com.example.project1

import android.content.res.Resources
import java.util.Locale
import java.util.Scanner

class Wordle(resources: Resources) {
    var wordToDiscover: String = ""
    private var currentTries: Int = 0
    val wordsList: ArrayList<String> = ArrayList()

    init {
        readFile(resources)
        pickRandomWord()
    }

    private fun readFile(resources: Resources) {
        val inputStream = resources.openRawResource(R.raw.words)
        val scanner = Scanner(inputStream)

        while (scanner.hasNextLine()) {
            wordsList.add(scanner.nextLine().trim().uppercase(Locale.ROOT))
        }
        scanner.close()
    }

    private fun pickRandomWord() {
        wordToDiscover = wordsList.random()
    }

    fun testJavac() {
        wordToDiscover = "JAVAC"
    }

    fun won(word: String): Boolean {
        return word == wordToDiscover
    }

    fun play(word: String): IntArray {
        val result = IntArray(WORD_LENGTH)
        val wordToDiscoverChars = wordToDiscover.toCharArray()
        val wordChars = word.toCharArray()

        val usedIndexes = BooleanArray(WORD_LENGTH)

        for (i in wordChars.indices) {
            if (wordChars[i] == wordToDiscoverChars[i]) {
                result[i] = 1
                usedIndexes[i] = true
            }
        }

        for (i in wordChars.indices) {
            if (result[i] == 1) continue // Already counted as correct position
            for (j in wordToDiscoverChars.indices) {
                if (!usedIndexes[j] && wordChars[i] == wordToDiscoverChars[j]) {
                    result[i] = 2
                    usedIndexes[j] = true
                    break
                }
            }
        }

        return result
    }

    companion object {
        const val MAX_TRIES = 6
        const val WORD_LENGTH = 5
    }
}
