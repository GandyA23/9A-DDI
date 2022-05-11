import kotlin.math.pow

/**
 * Supongamos que un prisma rectangular tiene las dimensiones:
 *  - largo = 15
 *  - ancho = 23
 *  - alto = 12
 * Obten su volumen.
 * */
fun prisma () {
    val largo : Double = 15.0
    val ancho : Double = 23.0
    val alto : Double = 12.0
    var v : Double = largo * ancho * alto

    println("El volumen del prisma es: $v")
}

/**
 * Obtén la energía cinética si la masa m = 12 kg y la velocidad es de v = 3 m/s,
 * imprime el resultado con solo dos decimales.
 * */
fun energiaCinetica () {
    val m : Double = 12.0
    val v : Double = 3.0
    val ec : Double = 0.5 * m * v.pow(2.0)

    println("Energía cinética: $ec")
}

/**
 * Uso de tipos de datos en variables
 * */
fun tipoVariables () {
    val val1 : Long = 1495970192837664L
    val val2 : Double = 12.5
    val val3 : Boolean = true
    val val4_1 : Byte = 90
    val val4_2 : Short = 90
    val val4_3 : Int = 90
    val val5 : String = "No tengo trono ni reina, ni nadie que me comprenda, pero sigo siendo el rey"
    val val6 : Double = 6.626070040
    val val7 : Float = 8.8f
}

/**
 * Costo promedio para 180 clics, donde cada uno de los clics tiene el siguiente costo:
 *  - Primeros 20 clics = $0.80 cada uno
 *  - Siguientes 60 clics = $0.30 cada uno
 *  - Siguientes 100 clics = $0.25 cada uno
 * Se deberá incluir el IVA al resultado total.
 */
val CLICKS_100 : Double = 0.25
val CLICKS_60 : Double = 0.30
val CLICKS_20 : Double = 0.80
val IVA = 0.16
fun promedioClics () {
    val clics : Int = 180
    var promedio : Double = (100 * CLICKS_100) + (60 * CLICKS_60) + (20 * CLICKS_20) / clics
    val iva : Double = promedio * IVA;
    println(String.format("""
        Costo promedio para 180 Clicks:
         - Promedio por click   : $%.2f
         - IVA                  : $%.2f
         - Total                : $%.2f
    """.trimIndent(), promedio, iva, iva + promedio))
}

fun main (args: Array<String>) {
    prisma()
    energiaCinetica()
    tipoVariables()
    promedioClics()
}
