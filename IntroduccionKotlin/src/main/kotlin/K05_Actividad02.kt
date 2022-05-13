fun readLine() = kotlin.io.readLine()!!
fun readInt() = readLine().toInt()
fun readDouble() = readLine().toDouble()

fun ej1 () {
    print("Dime la cantidad en miligramos: ")
    val mg = readInt()

    if (mg > 100)
        println("Felicidades, es buena poción multijugos")
    else
        println("La poción es mediocre, sangre sucia inmunda")
}

fun ej2 () {
   var flag : Boolean
    do {
        print("Dime la distancia del conductor: ")
        var distance = readDouble()

        print("¿El conductor esta disponible? [1.- SI, 2.- NO]: ")
        var available = readInt() == 1

        flag = distance <= 0.5 && available

        if (flag)
            println("Listo para el recorrido")
        else if (distance <= 0.5 && !available)
            println("Conductor cercano, pero no disponible")
        else if (distance > 0.5 && available)
            println("Conductor disponible pero muy lejos, aplicaran tarifas más altas")
        else
            println("No hay conductores disponibles")
    } while (!flag)
}

fun ej3 () {
    var sumaFor = 0L
    var sumaWhile = 0L

    print("Dime un número n: ")
    var n = readInt()


    for (i in 1 .. n)
        sumaFor += i

    while (n > 0)
        sumaWhile += n--

    println("La suma es $sumaWhile")
}

fun ej4 () {
    print("Dime un número y yo te digo el clima: ")
    var n = readInt()
    var clima : String? = null

    when (n) {
        1 -> clima = "Soleado"
        2 -> clima = "Nublado"
        3 -> clima = "Lluvioso"
        4 -> clima = "Tormentoso"
        5 -> clima = "Nevado"
    }

    if (clima != null)
        println("El clima de hoy es $clima")
    else
        println("El número se encuentra fuera de mi rango")
}

fun ej5 () {
    var maxLenght = Int.MIN_VALUE
    var pos = -1
    val pelicula : Array<String> = arrayOf("Jumanji", "Toy Story", "Pulp Fiction", "Batman: El caballero de la noche", "Kill Bill")

    for (i in 0 until pelicula.size) {
        if (pelicula[i].length > maxLenght) {
            pos = i
            maxLenght = pelicula[i].length
        }
    }

    println("La película con el nombre más largo es: ${pelicula[pos]} en la posición $pos")
}

fun ej6 () {
    var n = 0
    do {
        print("Inserte un número: ")
        n = readInt()

        if (n <= 0)
            println("Interte un número positivo")
    } while (n <= 0)

    println("Pares hasta $n")
    for (i in 2 .. n step 2)
        print("${i} ")
}

fun main (args: Array<String>) {
    ej1()
    ej2()
    ej3()
    ej4()
    ej5()
    ej6()
}
