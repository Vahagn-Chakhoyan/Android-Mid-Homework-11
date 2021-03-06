package com.vahagn.android_mid_homework_10

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import java.util.*

class Ex1 : AppCompatActivity() {
    private lateinit var timerText: TextView
    private lateinit var startTimer: Button

    private lateinit var timer: Timer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ex1)

        timerText = findViewById(R.id.timerText)
        startTimer = findViewById(R.id.startTimer)
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

        startTimer.isEnabled = true
        timer = Timer()
    }

    fun startTimer(view: View) {
        startTimer.isEnabled = false

        timer.schedule(object: TimerTask() {
            override fun run() {
                countDownStep()
            }}, 1000L, 1000L)
    }

    private fun countDownStep() {
        val seconds = timerText.text.toString().toInt() - 1

        if(seconds == 0) timer.cancel()

        timerText.post {
            timerText.text = seconds.toString()
        }
    }

    fun nextPage(view: View) {
        val intent = Intent(this, Ex1Page2::class.java)
        intent.putExtra(getString(R.string.seconds_key), timerText.text)
        startActivity(intent)
    }

    override fun onStop() {
        super.onStop()

        timer.cancel()
    }
}