package com.example.markupapp

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.navigation.ui.AppBarConfiguration
import com.example.markupapp.databinding.ActivityMainBinding
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import android.widget.Toast


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onSignupAct(view: View) {
        val myIntent = Intent(this, SignupMain::class.java)
        startActivity(myIntent)
    }

    fun mySecondActivity(view: View) {
        val itemLogin = findViewById<TextInputEditText>(R.id.fieldLogin)
        val itemLoginText = itemLogin.text.toString()
        val itemPass = findViewById<TextInputEditText>(R.id.fieldPass)
        if(itemLogin.text.toString() == "" || itemPass.text.toString() == "")
        {
            Toast.makeText(this, "Не заполнено одно из полей", Toast.LENGTH_LONG).show()
            return
        }
        val myIntent = Intent(this, SecondActivity::class.java)
        myIntent.putExtra("textValue", itemLoginText)
        startActivity(myIntent)
    }
}