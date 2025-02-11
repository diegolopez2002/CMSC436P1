
package com.example.project1

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var wordle: Wordle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        wordle = Wordle(resources)

        // Log the list of words
        for (word in wordle.wordsList) {
            Log.d("MainActivity", word)
        }

        // Log the selected word
        Log.d("MainActivity", "Word to discover: ${wordle.wordToDiscover}")

        for (word in wordle.wordsList) {
            val resultArray = wordle.play(word)
            Log.d("MainActivity", "$word ${arrayToString(resultArray)}")
        }

        wordle.testJavac()
        // Set the word to JAVAC
        Log.d("MainActivity", "Word to be discovered ${wordle.wordToDiscover}")

        // Log play results
        val testWords = arrayOf("STOPS", "JAVAC", "JAVAX", "VALID", "VITAL", "AACAC", "AVAAV")

        for (word in testWords) {
            val resultArray = wordle.play(word)
            Log.d("MainActivity", "$word ${arrayToString(resultArray)}")
        }
    }

    private fun arrayToString(arr: IntArray): String {
        return arr.joinToString(" ")
    }
}
