package com.example.bmical

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.Toast
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {
    private var heightInNum = 0.0
    private var weightInNum = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val height = findViewById<EditText>(R.id.height)
        val weight = findViewById<EditText>(R.id.weight)
        val bmiBtn = findViewById<Button>(R.id.bmiBtn)
        val girlrb = findViewById<RadioButton>(R.id.girlrb)
        val boyrb = findViewById<RadioButton>(R.id.boyrb)

        bmiBtn.setOnClickListener {
            if (height.text.isNullOrEmpty() || weight.text.isNullOrEmpty() || (!girlrb.isChecked && !boyrb.isChecked)) {
                Toast.makeText(this, "Please fill all the fields", Toast.LENGTH_SHORT).show()
            } else {
                val genderStr = if (girlrb.isChecked) girlrb.text.toString() else boyrb.text.toString()

                if (!height.text.isNullOrEmpty() && !weight.text.isNullOrEmpty()) {
                    heightInNum = height.text.toString().toDouble()
                    weightInNum = weight.text.toString().toDouble()

                    val intent = Intent(applicationContext, SecondActivity::class.java)
                    intent.putExtra("BMI", calculateBMI(weightInNum, heightInNum))
                    intent.putExtra("gender", genderStr)
                    startActivity(intent)

                    // Optional: Clear the input fields after starting the SecondActivity
                    height.text.clear()
                    weight.text.clear()
                }
            }
        }
    }

    // Updated the function to return BMI with two decimal places
    private fun calculateBMI(weightInKg: Double, heightInCm: Double): Double {
        // Convert height to meters
        val heightInM = heightInCm / 100.0

        // Calculate BMI using the formula: BMI = weight / (height^2)
        val bmi = weightInKg / (heightInM * heightInM)

        // Use DecimalFormat to format BMI with two decimal places
        val df = DecimalFormat("#.##")
        return df.format(bmi).toDouble()
    }
}
