package com.example.vk_android_course

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var etText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etText = findViewById(R.id.etText)

        // 1. Явный Intent
        findViewById<Button>(R.id.btnOpenSecond).setOnClickListener {
            val text = etText.text.toString().trim()

            if (text.isEmpty()) {
                Toast.makeText(this, "Введите текст!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val intent = Intent(this, SecondActivity::class.java).apply {
                putExtra("EXTRA_TEXT", text)
            }
            startActivity(intent)
        }

        // 2. Неявный Intent (ACTION_DIAL)
        findViewById<Button>(R.id.btnCallFriend).setOnClickListener {
            val phoneNumber = etText.text.toString().trim()

            if (phoneNumber.isEmpty()) {
                Toast.makeText(this, "Введите номер телефона!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val dialIntent = Intent(Intent.ACTION_DIAL).apply {
                data = Uri.parse("tel:$phoneNumber")
            }

            // Проверка наличия обработчика — защита от ActivityNotFoundException
            if (dialIntent.resolveActivity(packageManager) != null) {
                startActivity(dialIntent)
            } else {
                Toast.makeText(this, "Нет приложения для звонков", Toast.LENGTH_SHORT).show()
            }
        }

        // 3. Системный Intent (ACTION_SEND)
        findViewById<Button>(R.id.btnShareText).setOnClickListener {
            val shareText = etText.text.toString().trim()

            if (shareText.isEmpty()) {
                Toast.makeText(this, "Введите текст для отправки!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val shareIntent = Intent(Intent.ACTION_SEND).apply {
                type = "text/plain"
                putExtra(Intent.EXTRA_TEXT, shareText)
            }

            if (shareIntent.resolveActivity(packageManager) != null) {
                startActivity(Intent.createChooser(shareIntent, "Поделиться через..."))
            } else {
                Toast.makeText(this, "Нет приложений для отправки", Toast.LENGTH_SHORT).show()
            }
        }
    }
}