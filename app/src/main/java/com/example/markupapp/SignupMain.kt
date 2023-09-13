package com.example.markupapp

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.example.markupapp.databinding.ActivityMainBinding
import com.example.markupapp.databinding.ActivitySignupMainBinding
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout


class SignupMain : AppCompatActivity() {
    val db = DbHelper(this)
    private lateinit var binding: ActivitySignupMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button2.setOnClickListener {
            checkClick()
        }
    }

    fun backActivity(view: View) {
        val myIntent = Intent(this, MainActivity::class.java)
        startActivity(myIntent)
    }

    fun checkClick() {
        val name = binding.textName
        val email = binding.textEmail
        val pass = binding.textPass
        val passConfirm = binding.textPassConf

        if (name.text.toString() == "" || email.text.toString() == "" || pass.text.toString() == "" || passConfirm.text.toString() == "") {
            Toast.makeText(this, "ERROR: One or more fields are not filled", Toast.LENGTH_LONG).show()
            return
        }

        if (pass.text.toString() != passConfirm.text.toString()) {
            Toast.makeText(this, "ERROR: Passwords don't match", Toast.LENGTH_LONG).show()
            return
        }

        val cv = ContentValues()

        cv.put("name", name.text.toString())
        cv.put("email", email.text.toString())
        cv.put("password", pass.text.toString())
        cv.put("count", 0)

        db.writableDatabase.insert("users", null, cv)
    }
}