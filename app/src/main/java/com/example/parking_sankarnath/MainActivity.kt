package com.example.parking_sankarnath

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    // List to store Parking data
    private var parkingHistory: MutableList<String> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val test = "Sankarparking"

        // getting values from the UI Elements
        val etNumberOfHours: EditText = findViewById(R.id.etNumberOfHours)
        val etLicensePlate: EditText = findViewById(R.id.etLicensePlate)
        val tvError: TextView = findViewById(R.id.tvError)
        val tvResults: TextView = findViewById(R.id.tvResults)
        val btnPayNow: Button = findViewById(R.id.btnPayNow)
        val btnHistory: Button = findViewById(R.id.btnHistory)

        //input from radio button
        val radioLot: RadioGroup = findViewById(R.id.radioLots)

        // When the History button is pressed
        btnHistory.setOnClickListener {
            val intent = Intent(this, History::class.java)
            intent.putExtra("HISTORY_EXTRA", parkingHistory.toTypedArray())
            startActivity(intent)
        }

        btnPayNow.setOnClickListener{
            val NumberOfHoursText = etNumberOfHours.text.toString()
            val LicensePlateText = etLicensePlate.text.toString()

            // Check if the inputs are valid numbers and convert to int, null if not
            val NumberOfHours = NumberOfHoursText.toIntOrNull()

            // Getting the selected parking lot
            val selectedLot = radioLot.checkedRadioButtonId

            // If the inputs are null, throw an error message
            if (NumberOfHours == null) {
                tvError.visibility = TextView.VISIBLE
                tvError.text = "Error: All fields must be filled in."
                Log.d(test, "Here inside if null loop")
                tvResults.text = ""
            }
            else
            {
                // hide error message if already shown
                tvError.visibility = TextView.GONE

                var hourlyRate = 0.0
                var parkingLotText = "Lot A"
                if (selectedLot == R.id.radioLotA) hourlyRate = 2.5
                else
                {
                    hourlyRate = 3.0
                    parkingLotText = "Lot B"
                }


                val totalPaid = NumberOfHours * hourlyRate

                 //Format the results to 2 decimal places and output
                tvResults.text = "RECEIPT\n" +
                        "${parkingLotText}\n" +
                        "License Plate: ${LicensePlateText}\n" +
                        "Hours: ${NumberOfHours} \n" +
                        "Total Paid: $${String.format("%.2f", totalPaid)} \n"
                tvResults.visibility = TextView.VISIBLE
                Log.d(test, "Here before clearing")

                // Clear all
                etNumberOfHours.text.clear()
                etLicensePlate.text.clear()
                tvError.visibility = TextView.GONE

                // Clearing Radio button
                radioLot.clearCheck()

                // Save the purchase information
                parkingHistory.add("RECEIPT\n" +
                        "${parkingLotText}\n" +
                        "License Plate: ${LicensePlateText}\n" +
                        "Hours: ${NumberOfHours} \n" +
                        "Total Paid: $${String.format("%.2f", totalPaid)} \n"
                )

            }
        }

    }

}