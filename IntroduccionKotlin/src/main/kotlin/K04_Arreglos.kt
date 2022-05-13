fun main (args: Array<String>) {
    // Definición de un arreglo en diferentes tipos (valor estático)
    val arrayEntero : IntArray = intArrayOf(1, 2, 3, 4)
    val arrayDouble : DoubleArray = doubleArrayOf(1.0 , 2.0, 3.0, 4.0)
    val arrayBooleano : BooleanArray = booleanArrayOf(true, false, true, false)

    // Otra manera de definirlo es
    val arrayString : Array<String> = arrayOf("Gandy", "Esaú", "Ávila", "Estrada")
    val arrayInt : Array<Int> = arrayOf(1, 2, 3, 4)
    val arrayBool : Array<Boolean> = arrayOf(true, true, false)
    val arrayAny : Array<Any> = arrayOf(1, 2, 3, "Gandy", "Esaú")

    // Recorrer un arreglo
    for (num in arrayAny)
        print("${num} ")
    println()
}
