package com.example.myfirstapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val text = findViewById<TextView>(R.id.textView)
        val btnClickMe = findViewById<Button>(R.id.mybutton)
        var peopleCount = 0
        btnClickMe.setOnClickListener {
            ++peopleCount
            text.text = peopleCount.toString()
            Toast.makeText(this, "It's toast", Toast.LENGTH_LONG).show()
        }
    }
}