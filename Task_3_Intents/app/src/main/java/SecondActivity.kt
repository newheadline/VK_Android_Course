package com.example.vk_android_course

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val receivedText = intent.getStringExtra("EXTRA_TEXT") ?: "Текст не передан"

        findViewById<TextView>(R.id.tvReceivedText).text = receivedText
    }
}