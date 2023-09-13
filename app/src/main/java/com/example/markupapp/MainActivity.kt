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
import java.util.Dictionary


class MainActivity : AppCompatActivity() {
    // Save in the SharedPreferences that user has visited the app before

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button.setOnClickListener {
            mySecondActivity()
        }
    }

    fun onSignupAct(view: View) {
        val myIntent = Intent(this, SignupMain::class.java)
        startActivity(myIntent)
    }

    fun mySecondActivity() {
        val loginText = binding.fieldLogin.text.toString()
        val passText = binding.fieldPass.text.toString()

        val db = DbHelper(this)
        val cursor = db.writableDatabase.rawQuery("SELECT * FROM users WHERE email = ?", arrayOf(loginText))
        if(cursor.moveToFirst())
        {
            val pass = cursor.getColumnIndex("password")
            if (pass != -1) {
                if(cursor.getString(pass) == passText)
                {
                    val myIntent = Intent(this, SecondActivity::class.java)
                    db.writableDatabase.execSQL("UPDATE users SET count = count + 1 WHERE email = ?", arrayOf(loginText))
                    var countof = checkCount()
                    myIntent.putExtra("name", countof.keys.first())
                    myIntent.putExtra("count", countof.values.first())
                    startActivity(myIntent)
                }
                else
                {
                    Toast.makeText(this, "Неверный пароль", Toast.LENGTH_LONG).show()
                }
            }
        }
        else
        {
            Toast.makeText(this, "Пользователь не найден", Toast.LENGTH_LONG).show()
        }
        cursor.close()
    }

    // Check count of logins for every user in the database and return the user with maximum count of logins
    fun checkCount(): Map<String, Int> {
        val db = DbHelper(this)
        val cursor = db.writableDatabase.rawQuery("SELECT * FROM users", null)
        var maxCount = 0
        var maxUser = ""
        if(cursor.moveToFirst())
        {
            val email = cursor.getColumnIndex("email")
            val count = cursor.getColumnIndex("count")
            if (email != -1 && count != -1) {
                do {
                    if(cursor.getInt(count) > maxCount)
                    {
                        maxCount = cursor.getInt(count)
                        maxUser = cursor.getString(email)
                    }
                } while (cursor.moveToNext())
            }
        }
        cursor.close()
        Toast.makeText(this, "Пользователь с максимальным количеством логинов: $maxUser, " +
                "количество логинов: $maxCount", Toast.LENGTH_LONG).show()
        return mapOf(maxUser to maxCount)
    }
}