package com.example.calculator

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.text.DecimalFormat

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private var resultTv: TextView? = null
    private var solutionTv:TextView? = null
    private var buttonC: Button? = null
    private var buttonDivide: Button? = null
    private var buttonMultiply:Button? = null
    private var buttonPlus:Button? = null
    private var buttonMinus:Button? = null
    private var buttonEquals:Button? = null
    private var button0: Button? = null
    private var button1:Button? = null
    private var button2:Button? = null
    private var button3:Button? = null
    private var button4:Button? = null
    private var button5:Button? = null
    private var button6:Button? = null
    private var button7:Button? = null
    private var button8:Button? = null
    private var button9:Button? = null
    private var buttonAC: Button? = null
    private var buttonDot:Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        resultTv = findViewById(R.id.text_view_result)
        solutionTv = findViewById(R.id.text_view_solution)
        button0 = findViewById(R.id.button_0)
        assignId(buttonC,R.id.c_button)
        assignId(buttonDivide,R.id.div)
        assignId(buttonMultiply,R.id.multiply)
        assignId(buttonPlus,R.id.add)
        assignId(buttonMinus,R.id.sub)
        assignId(buttonEquals,R.id.equals)
        assignId(button0,R.id.button_0)
        assignId(button1,R.id.button_1)
        assignId(button2,R.id.button_2)
        assignId(button3,R.id.button_3)
        assignId(button4,R.id.button_4)
        assignId(button5,R.id.button_5)
        assignId(button6,R.id.button_6)
        assignId(button7,R.id.button_7)
        assignId(button8,R.id.button_8)
        assignId(button9,R.id.button_9)
        assignId(buttonAC,R.id.button_ac)
        assignId(buttonDot,R.id.button_dot);



    }
    fun assignId(_btn: Button?, id: Int) {
        var btn = _btn
        btn = findViewById(id)
        btn.setOnClickListener(this)

    }

    override fun onClick(view: View?) {
        val button: Button = view as Button
        val buttonText: String = button.text.toString()
        var dataToCalculate = solutionTv!!.text.toString()

        if (buttonText == "AC") {
            solutionTv!!.text = ""
            resultTv!!.text = "0"
            return
        }
        if (buttonText == "=") {
            val finalResult: String = getResult(dataToCalculate)
            resultTv!!.text = finalResult
            solutionTv!!.text = ""
            return
        }
        if(buttonText == "C"){
            if (solutionTv!!.text != "")
                dataToCalculate = dataToCalculate.substring(0,dataToCalculate.length - 1)
        }else{
            dataToCalculate += buttonText
        }
        solutionTv!!.text = dataToCalculate
    }

    private fun getResult(dataToCalculate: String): String {
        return try {
            val numbers = dataToCalculate.split("\\D".toRegex())
            val num1 = numbers[0].toDouble()
            val num2 = numbers[1].toDouble();
            val operation = dataToCalculate.replace(numbers[0], "").replace(numbers[1],"")

            if (operation == "/" && num2 == 0.0){
                Toast.makeText(applicationContext,"Ты чо дурак?)",Toast.LENGTH_SHORT).show()
                return "Err"
            }
            return when (operation) {
                "+" -> roundResult(num1 + num2)
                "-" -> roundResult(num1 - num2)
                "*" -> roundResult(num1 * num2)
                "/" -> roundResult(num1 / num2)
                else -> { // Note the block
                   "Err"
                }
            }
        } catch (e: Exception) {
            Toast.makeText(applicationContext,"Don't support More than one operation.",Toast.LENGTH_SHORT).show()
            "Err"
        }
    }
    private fun roundResult(result:Double) : String {
        val df = DecimalFormat("###.##")
        return df.format(result)
    }


}
