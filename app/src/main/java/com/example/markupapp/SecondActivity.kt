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
        val textValue2 = intent.getStringExtra("name")
        val loginCount = intent.getStringExtra("count")
        itemView.text = textValue2
        itemCount.text = "Count of logins: $loginCount, user: $textValue2"
    }
}