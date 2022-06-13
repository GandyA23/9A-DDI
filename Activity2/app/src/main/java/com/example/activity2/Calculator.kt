package com.example.activity2

import java.util.*

class Calculator {
    companion object {
        private const val SYNTAX_ERROR_MSG = "Syntax Error"
        private const val MATH_ERROR_MSG = "Math Error"

        fun rxNum (s : String) : Boolean {
            return Regex("[0-9]+").matches(s)
        }

        fun rxMajorOperation (s : String) : Boolean {
            return Regex("[x\\/]+").matches(s)
        }

        fun rxMinorOperation (s : String) : Boolean {
            return Regex("[\\-\\+]+").matches(s)
        }

        fun rxOperation (s : String) : Boolean {
            return rxMinorOperation(s) || rxMajorOperation(s)
        }
    }

    private fun validate (operations : MutableList<String>) : Boolean {
        if (rxMajorOperation(operations.first())) return false
        if (rxMinorOperation(operations.last()) || rxMajorOperation(operations.last())) return false

        for (i in 0 until operations.size) {
            // Verifica que no existan valores del mismo tipo y que se encuentren seguidos
            if (rxNum(operations[i]) && i+1 < operations.size && rxNum(operations[i+1])) return false
            if (rxMinorOperation(operations[i]) && i+1 < operations.size && rxMinorOperation(operations[i+1])) return false
            if (rxMajorOperation(operations[i]) && i+1 < operations.size && rxMajorOperation(operations[i+1])) return false
        }

        return true
    }

    private fun doOperation (a : String, b : String, operation : String) : String {
        var result : String

        val aAux : String = if (a.isBlank()) "0.0" else a
        val bAux : String = if (b.isBlank()) "0.0" else b

        try {
            result = (when (operation) {
                "+" -> aAux.toDouble() + bAux.toDouble()
                "-" -> if (aAux.equals("0.0")) bAux.toDouble() * -1
                    else aAux.toDouble() - bAux.toDouble()
                "x" -> aAux.toDouble() * bAux.toDouble()
                else -> aAux.toDouble() / bAux.toDouble()
            }).toString()
        } catch (e : Exception) {
            result = MATH_ERROR_MSG
        }

        println("Result: $a $operation $b = $result, A: $a, B: $b, Operation: $operation")

        return result
    }

    private fun emptyOperations(previousOperations: MutableList<String>): String {
        var result = "0.0"
        var lastOpe = ""
        var canMinus = false

        for (po in previousOperations) {
            println("po: $po")

            if (rxOperation(po))
                if (rxMajorOperation(lastOpe) && po.equals("-"))
                    canMinus = true
                else
                    lastOpe = po
            else {
                var poAux = po

                if (canMinus) {
                    poAux = "-$po"
                    canMinus = false
                }

                if (lastOpe.isNotBlank()) {
                    result = doOperation(result, poAux, lastOpe)
                    lastOpe = ""
                } else
                    result = doOperation(result, poAux, "+")
            }
        }

        if (canMinus)
            result = doOperation(result, "-1", "x")

        return result
    }

    private fun calculate (operations : MutableList<String>, pos : Int = 0, previousOperations : MutableList<String> = mutableListOf()) : Double {
        if (pos >= operations.size) {
            val result = emptyOperations(previousOperations)
            println("Final Result: $result")
            return result.toDouble()
        }

        var ope1 : String = operations[pos]

        println("ope1: $ope1")

        if (rxNum(ope1) && previousOperations.size > 1 && rxMajorOperation(previousOperations.last())) {
            val operator = previousOperations.removeLast()
            val ope2 : String = previousOperations.removeLast()
            ope1 = doOperation(ope2, ope1, operator)
        }

        previousOperations.add(ope1)

        return calculate(operations, pos + 1, previousOperations)
    }

    fun calculator (operation : String) : String {
        // Convierte el string a una Lista
        var listOperations : MutableList<String> = operation.trim().split(" ").filter { it.isNotBlank() && it.isNotEmpty() }.toMutableList()

        try {
            if (listOperations.size == 1)
                if (rxNum(listOperations[0]))
                    return listOperations[0]
                else return SYNTAX_ERROR_MSG
            else if (listOperations.isEmpty())
                return ""
            else if (!validate(listOperations))
                return SYNTAX_ERROR_MSG

            println("Resultado final: ${calculate(listOperations)}")

            return "${calculate(listOperations)}"
        } catch (e : Exception) {
            println("Ha ocurrido un error en la aplicación")
            println(e)
            return MATH_ERROR_MSG
        }
    }
}
