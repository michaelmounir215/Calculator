package com.example.calc.fragments

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.core.view.children
import com.example.calc.R
import com.example.calc.databinding.FragmentCalcBinding

class CalcFragment : AppCompatActivity() {

    private lateinit var binding: FragmentCalcBinding
    private var firstnumber = ""
    private var currentNumber = ""
    private var currentOperator = ""
    private var result = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_calc)

        binding.apply {
            // get all buttons
            binding.layoutMain.children.filterIsInstance<Button>().forEach { button ->
                //buttons click listener
                button.setOnClickListener {
                    //get clicked button text
                    val buttonText = button.text.toString()
                    when {
                        buttonText.matches(Regex("[0-9]")) -> {
                            if (currentOperator.isEmpty()) {
                                firstnumber += buttonText
                                tvResult.text = firstnumber
                            } else {
                                currentNumber += buttonText
                                tvResult.text = currentNumber
                            }
                        }

                        buttonText.matches(Regex("[+\\-*/]")) -> {
                            currentNumber = ""
                            if (tvResult.text.toString().isNotEmpty()) {
                                currentOperator = buttonText
                                tvResult.text = "0"
                            }
                        }

                        buttonText == "=" -> {
                            if (currentNumber.isNotEmpty() && currentOperator.isNotEmpty()) {
                                tvFormula.text = "$firstnumber$currentOperator$currentNumber"
                                result =
                                    evaluateExpression(firstnumber, currentNumber, currentOperator)
                                firstnumber = result
                                tvResult.text = result
                            }
                        }

                        buttonText == "." -> {
                            if (currentOperator.isEmpty()) {
                                if (!firstnumber.contains(".")) {
                                    if (firstnumber.isEmpty()) firstnumber += "0$buttonText"
                                    else firstnumber += buttonText
                                    tvResult.text = firstnumber
                                }
                            } else {
                                if (!currentNumber.contains(".")) {
                                    if (currentNumber.isEmpty()) currentNumber += "0$buttonText"
                                    else currentNumber += buttonText
                                    tvResult.text = currentNumber
                                }
                            }
                        }

                        buttonText == "C" -> {
                            currentNumber = ""
                            firstnumber = ""
                            currentOperator = ""
                            tvResult.text = "0"
                            tvFormula.text = ""
                        }
                    }
                }
            }


        }
    }

    //functions
    private fun evaluateExpression(
        firstNumber: String,
        secondNumber: String,
        operator: String
    ): String {
        val num1 = firstNumber.toDouble()
        val num2 = secondNumber.toDouble()
        return when (operator) {
            "+" -> (num1 + num2).toString()
            "-" -> (num1 - num2).toString()
            "*" -> (num1 * num2).toString()
            "/" -> (num1 / num2).toString()
            else -> ""
        }
    }
}
