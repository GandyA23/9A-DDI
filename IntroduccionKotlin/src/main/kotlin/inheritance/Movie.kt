package inheritance

/**
 * son
 * Realizar herencia de la clase Media
 * Para poder hacer la herencia, es necesario recibir los parámetros para la clase padre y mandarselos.
 * Ahora Movie puede hacer uso de las mismas propiedades y métodos de Media
 * */
class Movie (name: String, duration : Int, isAvailable : Boolean) : Media (name, duration, isAvailable){
}
