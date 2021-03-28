package com.vahagn.android_mid_homework_10

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import java.util.*

class Ex1Page2 : AppCompatActivity() {
    private lateinit var timerText: TextView

    private lateinit var timer: Timer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ex1_page2)

        timerText = findViewById(R.id.timerText)
        timerText.text = intent.getCharSequenceExtra(getString(R.string.seconds_key))
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        timerText.text = savedInstanceState.getCharSequence(getString(R.string.seconds_key))
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putCharSequence(getString(R.string.seconds_key), timerText.text)
    }

    override fun onStart() {
        super.onStart()
        timer = Timer()
        if(timerText.text == "0") return

        timer.schedule(object: TimerTask() {
            override fun run() {
                countDownStep()
            }}, 1000L, 1000L)
    }

    private fun countDownStep() {
        val seconds = timerText.text.toString().toInt() - 1
        Log.i("homework", "STEP")

        if(seconds == 0) timer.cancel()

        timerText.post {
            timerText.text = seconds.toString()
        }
    }

    override fun onStop() {
        super.onStop()

        timer.cancel()
    }
}