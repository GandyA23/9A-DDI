// Declaración de la función main

// El parámetro args es te tipo Array<String>
// No es necesario declarar los tipos en los parámetros, pero se hace por buenas prácticas.

// Los argumentos fueron enviados desde la configuración de run dentro de Intellij (Program arguments)
// Usado principalmente para testing
fun main (args: Array<String>) {
    // En Kotlin, no es necesario el uso de puntos y comas al final de cada sentencia
    // Template string con los argumentos enviados
    println("Hola ${args[0]} ${args[1]} ${args[2]} ${args[3]}")

    // La diferencia entre print y println es que println imprime un salto de línea al final del string
    print("Hola ${args[0]} ${args[1]} ${args[2]} ${args[3]}")

    /**
     * Uso de variables en Kotlin
     *
     * Es necesario la palabra reservada var para definir una variable
     * La diferencia principal entre las variables y las constantes es que las constantes solo es posible asignarles
     * un dato durante su definición, mientras que la variable puede asignarle un dato durante cualquier momento de la ejecución.
     *
     * */
    // Manera 1: La más simple
    var sexo1 = true
    var altura1 = 1.69
    var promedio1 = 7.50f
    var anio1 = 2001

    // Manera 2: Definiendo el tipo de dato
    var sexo2 : Boolean = false
    var altura2 : Double = 1.69999998
    var promedio2 : Float = 7.50f
    var anio2 : Int = 2001

    /**
     * Uso de constantes en Kotlin
     * Es necesario la palabra reservada val para definir una variable
     * */
    // Manera 1: La más simple
    val CURP_1 = "AIEGXXXXXXXXXXXXX"

    // Manera 2: Definiendo el tipo de dato
    val CURP_2 : String = "AIEGXXXXXXXXXXXXX"

    /**
     * Uso de template strings con variables
     *
     * Las llaves son más usadas cuando nos referimos a una posición específica de un arreglo o al momento
     * de hacer un llamado de una función
     *
     * Se coloca un salto de línea al inicio debido al print anterior
     * **/
    println("\nMi CURP es $CURP_1 y nací en el año $anio1")

    /** Uso de String.format **/
    println(String.format("Mi altura es %.2f y mi CURP es %s", altura2, CURP_1))
}
