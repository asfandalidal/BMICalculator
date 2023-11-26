
package com.example.bmical

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.second_activity)

        val intent = intent
        val bmiVal = intent.getDoubleExtra("BMI", 0.0)
        val viewMesg = findViewById<TextView>(R.id.tvMesg)
        val viewBMI = findViewById<TextView>(R.id.tvBMI)
        val imageView = findViewById<ImageView>(R.id.ivGender)
        val bmiStr = bmiVal.toString()
        val gender = intent.getStringExtra("gender")
        var mesg = ""

        // BMI Range Constants
        val underweightRange = 18.0
        val fitMinRange = 18.0
        val fitMaxRange = 24.0

        // BMI Message
        if (bmiVal < underweightRange) {
            mesg = "You are underweight $gender !"
            // Display boy or girl photo based on gender
            if (gender == "Boy") {
                imageView.setImageResource(R.drawable.boy_normal)
            } else if (gender == "Girl") {
                imageView.setImageResource(R.drawable.girl_normal)
            }
        } else if (bmiVal in fitMinRange..fitMaxRange) {
            mesg = "You are fit $gender !"
            // Display boy or girl fit photo based on gender
            if (gender == "Boy") {
                imageView.setImageResource(R.drawable.boy_normal)
            } else if (gender == "Girl") {
                imageView.setImageResource(R.drawable.girl_normal)
            }
        } else {
            mesg = "You are overweight $gender !"
            // Display boy or girl overweight photo based on gender
            if (gender == "Boy") {
                imageView.setImageResource(R.drawable.boy_overweight)
            } else if (gender == "Girl") {
                imageView.setImageResource(R.drawable.girl_overweight)
            }
        }

        // Set TextViews
        viewBMI.text = bmiStr
        viewMesg.text = mesg
    }
}
