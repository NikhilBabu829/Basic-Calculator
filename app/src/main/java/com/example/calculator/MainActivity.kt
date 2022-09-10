package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.lang.ArithmeticException

class MainActivity : AppCompatActivity() {
    var lastNumeric : Boolean = false;
    var lastDecimal : Boolean = false;
    private var display : TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        display = findViewById(R.id.displayText)
    }
    fun onDigit(view:View){
        display?.append((view as Button).text);
        lastNumeric = true;
        lastDecimal = false;
    }
    fun onClear(view:View){
        display?.text = ""
        lastNumeric = false;
        lastDecimal = false;
    }
    fun onDecimal(view: View){
        if(lastNumeric && !lastDecimal){
            display?.append(".");
            lastDecimal = true;
            lastNumeric = false;
        }
    }

    fun onEquals(view: View){
        if(lastNumeric){
            var displayValue = display?.text.toString()
            var prefix = "";
            if(displayValue.startsWith("-")) {
                prefix = "-";
                displayValue = displayValue.substring(1);
            }
            try {
                if(displayValue.contains("-")) {
                    var valueAfterSplit = displayValue.split("-")
                    var one = valueAfterSplit[0];
                    var two = valueAfterSplit[1];
                    if(prefix.isNotEmpty()){
                        one = prefix + one;
                    }
                    display?.text = (one.toDouble() - two.toDouble()).toString();
                }
                if(displayValue.contains("x")) {
                    var valueAfterSplit = displayValue.split("x")
                    var one = valueAfterSplit[0];
                    var two = valueAfterSplit[1];
                    if(prefix.isNotEmpty()){
                        one = prefix + one;
                    }
                    display?.text = (one.toDouble() * two.toDouble()).toString();
                }
                if(displayValue.contains("/")) {
                    var valueAfterSplit = displayValue.split("/")
                    var one = valueAfterSplit[0];
                    var two = valueAfterSplit[1];
                    if(prefix.isNotEmpty()){
                        one = prefix + one;
                    }
                    display?.text = (one.toDouble() / two.toDouble()).toString();
                }
                if(displayValue.contains("+")) {
                    var valueAfterSplit = displayValue.split("+")
                    var one = valueAfterSplit[0];
                    var two = valueAfterSplit[1];
                    if(prefix.isNotEmpty()){
                        one = prefix + one;
                    }
                    display?.text = (one.toDouble() + two.toDouble()).toString();
                }
            }catch (e:ArithmeticException){
                e.printStackTrace()
            }
        }
    }

    fun onOperator(view: View){
        display?.text.let {
            if(lastNumeric && !onOperatorAdded(it.toString())){
                display?.append((view as Button).text)
                lastNumeric = false
                lastDecimal = false
            }
        }
    }

    fun onOperatorAdded(value: String):Boolean{
        return if(value.startsWith("-")){
            false
        }
        else{
            value.contains("+")||value.contains("-")||value.contains("x")||value.contains("/")
        }
    }


}