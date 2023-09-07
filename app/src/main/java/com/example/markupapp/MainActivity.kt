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
        /*val itemLogin = findViewById<TextInputEditText>(R.id.fieldLogin)
        val itemLoginText = itemLogin.text.toString()
        val itemPass = findViewById<TextInputEditText>(R.id.fieldPass)
        if(itemLogin.text.toString() == "" || itemPass.text.toString() == "")
        {
            Toast.makeText(this, "Не заполнено одно из полей", Toast.LENGTH_LONG).show()
            return
        }
        val myIntent = Intent(this, SecondActivity::class.java)
        myIntent.putExtra("textValue", itemLoginText)
        startActivity(myIntent)*/

        val itemLogin = findViewById<TextInputEditText>(R.id.fieldLogin)
        val itemLoginText = itemLogin.text.toString()
        val itemPass = findViewById<TextInputEditText>(R.id.fieldPass)
        val db = DbHelper(this)
        val cursor = db.writableDatabase.rawQuery("SELECT * FROM users WHERE email = ?", arrayOf(itemLoginText))
        if(cursor.moveToFirst())
        {
            val pass = cursor.getColumnIndex("password")
            if (pass != -1) {
                if(cursor.getString(pass) == itemPass.text.toString())
                {
                    val myIntent = Intent(this, SecondActivity::class.java)
                    db.writableDatabase.execSQL("UPDATE users SET count = count + 1 WHERE email = ?", arrayOf(itemLoginText))
                    var countof = checkCount()
                    myIntent.putExtra("name", countof[0].toString())
                    myIntent.putExtra("count", countof[1].toString())
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
    fun checkCount(): Array<Any> {
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
        return arrayOf(maxUser, maxCount)
    }
}