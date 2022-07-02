package com.example.mycalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.lang.ArithmeticException

class MainActivity : AppCompatActivity() {
    private var tvInput : TextView? = null
    private var dotUsed : Boolean = false
    private var lastNumeric : Boolean = true
    private var empty : Boolean = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tvInput = findViewById(R.id.tvInput)
    }

    fun onDigit(view: View)  {
        tvInput?.append((view as Button).text)
        lastNumeric = true
        empty = false
    }

    fun onClear(view: View) {
        tvInput?.text = ""
        dotUsed = false
        lastNumeric = false
        empty = true
    }

    fun onDecimalPoint(view: View)  {
        if(!dotUsed && tvInput?.text != "") {
            tvInput?.append(".")
            dotUsed = true
            lastNumeric = false
            empty = false
        }
    }

    fun onOperator(view: View)  {
        tvInput?.text?.let{
            if(empty && (view as Button).text == "-") {
                tvInput?.append(view.text)
                lastNumeric = false
                empty = false
            }
            else if(lastNumeric  && !isOperatorAdded(it.toString())) {
                tvInput?.append((view as Button).text)
                lastNumeric = false
                empty = false
            }
        }
    }

    private fun removeZeroAfterDot(result : String) : String {
        var value = result
        if(result.contains(".0"))   {
            value = value.substring(0, value.length - 2)
        }
        return value
    }

    private fun isOperatorAdded(value: String) : Boolean{
        var temp : String = value
        if(value == "-") {
            return true
        }
        if(value.startsWith("-")){
            temp = value.substring(1)
        }
        return temp.contains("/") || temp.contains("*") || temp.contains("+") || temp.contains("-")
    }

    fun onCalculate(view: View) {
        if(lastNumeric) {
            var value = tvInput?.text.toString()
            var firstNegative : Boolean = false
            try{
                if(value.startsWith("-"))   {
                    firstNegative = true
                    value = value.substring(1)
                }
                if(value.contains("-")) {
                    val splitValue = value.split("-")
                    var firstOperand = splitValue[0].toDouble()
                    if(firstNegative)   {
                        firstOperand = -firstOperand
                    }
                    var secondOperand = splitValue[1].toDouble()
                    var result = firstOperand - secondOperand
                    tvInput?.text = result.toString()
                }
                else if(value.contains("+")) {
                    val splitValue = value.split("+")
                    var firstOperand = splitValue[0].toDouble()
                    if(firstNegative)   {
                        firstOperand = -firstOperand
                    }
                    var secondOperand = splitValue[1].toDouble()
                    var result = firstOperand + secondOperand
                    tvInput?.text = result.toString()
                }
                else if(value.contains("*")) {
                    val splitValue = value.split("*")
                    var firstOperand = splitValue[0].toDouble()
                    if(firstNegative)   {
                        firstOperand = -firstOperand
                    }
                    var secondOperand = splitValue[1].toDouble()
                    var result = firstOperand * secondOperand
                    tvInput?.text = result.toString()
                }
                else if(value.contains("/")) {
                    val splitValue = value.split("/")
                    var firstOperand = splitValue[0].toDouble()
                    if(firstNegative)   {
                        firstOperand = -firstOperand
                    }
                    var secondOperand = splitValue[1].toDouble()
                    var result = firstOperand / secondOperand
                    tvInput?.text = result.toString()
                }
            }catch (e: ArithmeticException){
                e.printStackTrace()
            }
            tvInput?.text = removeZeroAfterDot(tvInput?.text.toString())
        }
    }
}