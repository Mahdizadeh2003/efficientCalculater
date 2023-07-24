package com.efficientcalculater

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton

class MainActivity : AppCompatActivity() {
    private val input = mutableListOf<String>()
    private var resultTextBox: TextView? = null
    private var infixExpression: Expression? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        resultTextBox = findViewById(R.id.resultTextBox)
    }

    fun onClick(button: View) {
        when (val buttonText = (button as AppCompatButton).text.toString()) {
            "=" -> {
                infixExpression = Expression(input)
                resultTextBox?.text = infixExpression!!.evaluateExpression().toString()
            }

            "CL" -> {
                input.clear()
                resultTextBox?.text = ""
            }

            "C" -> {
                resultTextBox?.text = "${resultTextBox?.text}".dropLast(input.last().length)
                input.remove(input.last())
            }

            else -> {
                if (Character.isDigit(buttonText[0])) {
                    if (input.isNotEmpty() && Character.isDigit(input.last()[0])) {
                        input[input.lastIndex] = input.last() + buttonText
                        resultTextBox?.text = "${resultTextBox?.text}${button.text}"
                    } else {
                        input.add(buttonText)
                        resultTextBox?.text = "${resultTextBox?.text} ${button.text}"
                    }

                } else {
                    input.add(buttonText)
                    resultTextBox?.text = "${resultTextBox?.text} ${button.text}"
                }

            }

        }

    }
}