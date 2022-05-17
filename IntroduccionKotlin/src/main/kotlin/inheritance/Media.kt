package inheritance

/**
 * dad class
 * Es necesario la palabra reservada open para poder hacer la herencia a sus hijos.
 * Es necesaria la palabra var para que pueda ser utilizada por sus hijos y en la misma clase padre.
 * La clase padre es la que se encarga de hacer referencias en memoria.
 * */
open class Media (var name : String, var duration : Int, var isAvailable : Boolean, var chapters : Int = 0){
    /**
     * companion object es para la creación de constantes dentro del objeto,
     * no es necesario crear una instancia de memoria para poder acceder a estas constantes (siempre y cuando no se declaren como private)
     * como las variables y funciones estáticas dentro de otros lenguajes como Java
     * */
    companion object {
        private const val FAME_RATE_HD = 68
        private const val FAME_RATE_SD = 24
        private const val SECONDS_IN_A_MINUTE = 60

        fun getTotalFrames (duration : Int, isHd: Boolean) : Int {
            return if (isHd) FAME_RATE_HD * SECONDS_IN_A_MINUTE * duration
            else FAME_RATE_SD * SECONDS_IN_A_MINUTE * duration
        }
    }

    fun play () : String {
        // Retorna un Pair<Int, String> asigna el Int a PLUS y el String a TYPE
        val (PLUS, TYPE) = if (chapters > 0) (0 to "serie") else (1 to "película")

        // return combinado con un if
        return if (this.isAvailable)
            "Reproduciendo la $TYPE $name con una duración de ${(chapters + PLUS) * duration} minutos"
        else
            "No se encuentra disponible"
    }

    fun stop () : String{
        return "Pausando $name..."
    }
}
