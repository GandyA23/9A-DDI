package inheritance

fun main (args : Array<String>) {
    // Crea una instancia de Movie
    val spiderman = Movie("The Batman", 180, true)

    // Crea una instancia de Serie
    val pelusaCaligari = Serie("Pelusa Caligari", 30, true, 25)

    println(spiderman.play())
    println(pelusaCaligari.play())

    // Es posible acceder a getTotalFrames debido a que se encuentra dentro de companion object en la clase Media
    println(Media.getTotalFrames(spiderman.duration, true))
    println(Media.getTotalFrames(pelusaCaligari.duration, false))
}
