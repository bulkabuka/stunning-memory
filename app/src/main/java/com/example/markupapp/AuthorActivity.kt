package com.example.markupapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.markupapp.databinding.ActivityAuthorBinding
import com.example.markupapp.databinding.ActivitySignupMainBinding

class AuthorActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAuthorBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_author)
        binding = ActivityAuthorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonStart.setOnClickListener {
            onStartClick()
        }

        val sharedPref = getSharedPreferences("com.example.markupapp", MODE_PRIVATE)
        val firstTime = sharedPref.getBoolean("firstTime", true)
        if (!firstTime) {
            val myIntent = intent
            myIntent.setClass(this, MainActivity::class.java)
            startActivity(myIntent)
        }
    }

    fun onStartClick() {
        val sharedPref = getSharedPreferences("com.example.markupapp", MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putBoolean("firstTime", false)
        editor.apply()
        val myIntent = Intent(this, MainActivity::class.java)
        startActivity(myIntent)
    }
}