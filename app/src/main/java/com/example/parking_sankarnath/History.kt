package com.example.parking_sankarnath

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class History : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        // When the Back button is pressed
        val backButton: Button = findViewById(R.id.backButton)
        backButton.setOnClickListener {
            finish()  // Close the History Activity
        }

        val tvHistory: TextView = findViewById(R.id.tvHistory)

        // Getting the data from the Intent and displaying it in a TextView
        val parkingHistory = intent.getStringArrayExtra("HISTORY_EXTRA") ?: arrayOf()
        tvHistory.text = parkingHistory.joinToString("\n")
    }
}