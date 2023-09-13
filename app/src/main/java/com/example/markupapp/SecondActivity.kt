package com.example.markupapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.markupapp.databinding.ActivityMainBinding
import com.example.markupapp.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySecondBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val itemView = binding.textView7
        val itemCount = binding.textView8
        val textValue2 = intent.getStringExtra("name")
        val loginCount = intent.getIntExtra("count", 0)
        itemView.text = textValue2
        itemCount.text = "Count of logins: $loginCount, user: $textValue2"
    }
}