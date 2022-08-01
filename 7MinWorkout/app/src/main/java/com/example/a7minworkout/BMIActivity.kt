package com.example.a7minworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import com.example.a7minworkout.databinding.ActivityBmiBinding
import java.math.BigDecimal
import java.math.RoundingMode

class BMIActivity : AppCompatActivity() {

    private var binding : ActivityBmiBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBmiBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        setSupportActionBar(binding?.toolbarBmiActivity)
        if(supportActionBar != null)    {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.title = "BMI Calculator"
        }
        binding?.toolbarBmiActivity?.setNavigationOnClickListener{
            onBackPressed()
        }
        binding?.btnCalculateUnits?.setOnClickListener{
            if(validateMetricUnits())   {
                val heightValue : Float = binding?.etMetricUnitHeight?.text.toString().toFloat() / 100
                val weightValue : Float = binding?.etMetricUnitWeight?.text.toString().toFloat()

                val bmi = weightValue / (heightValue * heightValue)

                displayBMIResult(bmi)
            }
            else    {
                Toast.makeText(this, "Please fill out appropriate values for height and weight", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun displayBMIResult(bmi : Float)   {
        val bmiLabel : String
        val bmiDescription : String

        if(bmi.compareTo(16f) <= 0)     {
            bmiLabel = "Severely underweight"
            bmiDescription = "You need to eat a lot more!"
        }
        else if(bmi.compareTo(16f) > 0 && bmi.compareTo(18.5f) <= 0)    {
            bmiLabel = "Underweight"
            bmiDescription = "You need to eat a lot more!"
        }
        else if(bmi.compareTo(18.5f) > 0 && bmi.compareTo(25f) <= 0)    {
            bmiLabel = "Normal"
            bmiDescription = "Congratulations! You are in a good shape!"
        }
        else if(bmi.compareTo(25f) > 0 && bmi.compareTo(30f) <= 0)    {
            bmiLabel = "Overweight"
            bmiDescription = "You should workout more and lose some weight!"
        }
        else    {
            bmiLabel = "Severely overweight"
            bmiDescription = "You should workout more and lose some weight!"
        }
        val bmiValue = BigDecimal(bmi.toDouble()).setScale(2, RoundingMode.HALF_EVEN).toString()
        binding?.tvBMIValue?.text = bmiValue
        binding?.tvBMIType?.text = bmiLabel
        binding?.tvBMIDescription?.text = bmiDescription
        binding?.llDisplayBMIResult?.visibility = View.VISIBLE
    }


    private fun validateMetricUnits() : Boolean {
        var isValid = true
        if(binding?.etMetricUnitWeight?.text.toString().isEmpty())  {
            isValid = false
        }
        else if(binding?.etMetricUnitHeight?.text.toString().isEmpty())  {
            isValid = false
        }
        return isValid
    }
}