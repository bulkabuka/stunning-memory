package com.example.markupapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        val itemView = findViewById<TextView>(R.id.textView7)
        val itemCount = findViewById<TextView>(R.id.textView8)
        val textValue2 = intent.getStringExtra("textValue")
        val loginCount = intent.getIntExtra("loginCount", 0)
        itemView.text = textValue2
        itemCount.text = "Count of logins: $loginCount"
    }
}