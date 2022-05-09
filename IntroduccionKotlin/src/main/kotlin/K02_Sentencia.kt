fun main (args : Array<String>) {
    // if - else
    val edad = 21

    // Cuando es solo 1 línea después de la condición, no es necesario usar las llaves
    if (edad >= 18)
        println("Eres mayor de edad")
    else if (edad <= 0)
        println("Aún no nace")
    else
        println("Eres menor de edad")

    // when (Sustituye a switch en otros lenguajes)
    val diaSemana : Int = 3

    // Cuando es solo 1 línea después de la condición, no es necesario usar las llaves
    when (diaSemana) {
        1 -> println("Lunes")
        2 -> println("Martes")
        3 -> println("Miercoles")
        4 -> println("Jueves")
        5 -> println("Viernes")
        6 -> println("Sabado")
        7 -> println("Domingo")
        // Debido a que son más de una sentencia, entonces es necesario poner las llaves
        else -> {
            print("Ha currido un")
            println("error")
        }
    }

    // for
    // Cuando es solo 1 línea después de la condición, no es necesario usar las llaves
    val n = 10
    println("Secuencia de 1 a $n: ")
    // 1 <= i <= n
    for (i in 1 .. n)
        print("$i ")
    println()

    // for (Paso diferente a 1)
    println("Secuencia de 1 a $n (paso de 2): ")
    // 1 <= i <= n
    for (i in 1 .. n step 2)
        print("$i ")
    println()

    // for until (until nos ayuda a que no considere el último valor del rango del for)
    // 1 <= i < n
    println("Secuencia de 1 a ${n-1}: ")
    for (i in 1 until n)
        print("$i ")
    println()

    // Recorriendo los argumentos
    // 1 <= i < args.size
    println("Recorriengo los argumentos de la función main: ")
    for (i in 0 until args.size)
        print("${args[i]} ")
    println()

    // while
    var minutos = 10
    println("Secuencia hacia abajo (while): ")
    while (minutos >= 0)
        print("${minutos--} ")
    println()

    // do - while
    println("Secuencia hacia abajo (do-while): ")
    do {
        print("${minutos--} ")
    }while(minutos >= 0)
    println()
}
