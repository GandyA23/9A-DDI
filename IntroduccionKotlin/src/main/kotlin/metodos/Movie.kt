package metodos

// Clase con sus propiedades
// Cuando se declaran las propiedades de esta manera, Kotlin ya genera un constructor
class Movie (var name: String, var duration: Int, var isAvailable: Boolean = true, var genre : String? = null) {
    /**
     * Propiedades con valores default
     * Otra manera posible
     * Como no se le asigna un identificador de acceso, el default será public
     * val name = "Spiderman"
     * val duration: Int = 90
     * val isAvailable : Boolean = true
     */

    /** Métodos dentro de la clase */
    fun readName (): String  {
        return this.name
    }

    // Unit es como el void en Java
    fun playMovie (name: String) : Unit {
        println("Reproduciendo la película $name")
    }
}

fun main (args : Array<String>) {
    // Crea la instancia de Movie
    val movie = Movie("The Batman", 180)

    // Acceder a las propiedades de la clase
    println(movie.name)

    movie.playMovie("Flash")
    movie.playMovie("Aquaman")

    /**
     * Uso de ? y !!
     *
     * ? - No le importan los NullPointerException
     * !! - Muestra los mensajes de NullPointerException
     */
    val number : Int? = null
    val number2 : Int = 2
    var resultWithQuestion = number?.plus(number2)      // No muestra el mensaje de NullPointerException y continúa con la ejecución
    println("Resultado: ${resultWithQuestion}")

    // var resultWithAdm = number!!.plus(number2)      // Muestra el mensaje de NullPointerException y termina la ejecución
    // println("Resultado: ${resultWithAdm}")
}
