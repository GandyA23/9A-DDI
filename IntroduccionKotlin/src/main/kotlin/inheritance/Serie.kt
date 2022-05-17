package inheritance

/**
 * son
 * Realizar herencia de la clase Media
 * Para poder hacer la herencia, es necesario recibir los parámetros para la clase padre y mandarselos.
 * Ahora Serie puede hacer uso de las mismas propiedades y métodos de Media
 * */
class Serie (name: String, duration : Int, isAvailable : Boolean, chapters: Int) : Media (name, duration, isAvailable, chapters) {
}
