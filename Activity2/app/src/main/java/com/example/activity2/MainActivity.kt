package com.example.activity2

import android.app.Activity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.activity2.databinding.ActivityMainBinding

class MainActivity : Activity() {

    companion object {
        fun rxNum (s : String) : Boolean {
            return Regex("[0-9]").matches(s)
        }

        fun rxOperation (s : String) : Boolean {
            return Regex("[+\\-x/]").matches(s)
        }
    }

    private lateinit var binding: ActivityMainBinding

    private fun calculateResult (ope : TextView) : String {
        return ""
    }

    private fun concateOperation (ope : TextView, v : String) : String {
        return "${ope.text}${if (rxNum(v)) v else " $v "}"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val result : TextView = binding.result

        val buttons : Map<String, Button> = mapOf(
            Pair("0", binding.btn0), Pair("1", binding.btn1), Pair("2", binding.btn2),
            Pair("3", binding.btn3), Pair("4", binding.btn4), Pair("5", binding.btn5),
            Pair("6", binding.btn6), Pair("7", binding.btn7), Pair("8", binding.btn8),
            Pair("9", binding.btn9), Pair("+", binding.btnPlus), Pair("-", binding.btnMinus),
            Pair("x", binding.btnMultiplication), Pair("/", binding.btnDivision)
        )

        val equal : Button = binding.btnEqual

        val c : Button = binding.btnC

        // Concatena al resultado para formar la operación
        for ((k, b) in buttons)
            b.setOnClickListener {
                result.text = concateOperation(result, k)
            }

        // Elimina todas las operaciones
        c.setOnClickListener {
            result.text = ""
        }

        // Realiza la operación
        equal.setOnClickListener {
            result.text = calculateResult(result)
        }
    }
}
