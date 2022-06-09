package com.example.activity2

class Calculator {
    companion object {
        private const val ERROR_MSG = "Syntax Error"

        fun rxNum (s : String) : Boolean {
            return Regex("[0-9]").matches(s)
        }

        fun rxMajorOperation (s : String) : Boolean {
            return Regex("[x/]").matches(s)
        }

        fun rxMinorOperation (s : String) : Boolean {
            return Regex("[\\-+]").matches(s)
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

    private fun convert (operations : MutableList<String>) : MutableList<String> {
        val listAux : MutableList<String> = mutableListOf()
        var flag = false

        for (i in 0 until operations.size) {
            var v = operations[i]

            if (flag) {
                flag = false
                continue
            }

            if (rxMinorOperation(v) && i+1 < operations.size && rxNum(operations[i+1])) {
                flag = true
                v = "${if (v.equals("-")) "${v}${operations[i+1]}" else operations[i+1] }"
            }

            listAux.add(v)
        }

        return listAux
    }

    private fun doOperation (a : String, b : String, operation : String) : String {
        var result : String

        try {
            result = (when (operation) {
                "+" -> a.toDouble() + b.toDouble()
                "x" -> a.toDouble() * b.toDouble()
                else -> a.toDouble() / b.toDouble()
            }).toString()
        } catch (e : Exception) {
            result = ERROR_MSG
        }

        return result
    }

    fun calculate (operation : String) : String {
        // Convierte el string a una Lista
        var listOperations : MutableList<String> = operation.split(" ").toMutableList()

        try {
            if (listOperations.size == 1)
                if (rxNum(listOperations[0]))
                    return listOperations[0]
                else return ERROR_MSG
            else if (listOperations.isEmpty())
                return ""
            else if (!validate(listOperations))
                return ERROR_MSG

            listOperations = convert(listOperations)

            var doMajorOperation : Boolean

            do {
                var listAux : MutableList<String> = mutableListOf()
                var i = -1
                var flag = false
                doMajorOperation = false

                while (++i < listOperations.size) {
                    if (flag) {
                        flag = false
                        continue
                    }

                    if (rxMajorOperation(listOperations[i])) {
                        flag = true
                        doMajorOperation = true
                        var newNum = doOperation(listOperations[i-1], listOperations[i+1], listOperations[i])
                        listAux[listAux.size-1] = "0"
                        listAux.add(newNum)
                        listOperations[i+1] = newNum
                    } else {
                        listAux.add(listOperations[i])
                    }
                }

                listOperations = listAux

            } while (doMajorOperation)

            var result = "0"

            for (i in listOperations)
                result = doOperation(result, i, "+")

            return result
        } catch (e : Exception) {
            println("Ha ocurrido un error en la aplicación")
            println(e)
            return ERROR_MSG
        }
    }
}