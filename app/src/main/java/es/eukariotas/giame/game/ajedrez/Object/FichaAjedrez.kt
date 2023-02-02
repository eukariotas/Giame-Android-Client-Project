package es.eukariotas.giame.game.ajedrez.Object

data class FichaAjedrez(
    val color: String,
    val tipo: String,
) {

    /**
     * Funcion que devuelve las posiciones a las que puede moverse la ficha
     */
    fun getPosiblePosition(posicion:String):List<String>{
            when (this.tipo) {
                "peon" -> return posibleMovePeon(posicion)
                "torre" -> return posibleMoveTorre(posicion)
                "caballo" -> return posibleMoveCaballo(posicion)
                "alfil" -> return posibleMoveAlfil(posicion)
                "reina" -> return posibleMoveReina(posicion)
                "rey" -> return posibleMoveRey(posicion)
                else -> return listOf()
            }

    }

    /**
     * Funcion que devuelve las posiciones posibles de una torre
     */
    fun posibleMoveTorre(posicion:String):List<String>{
        val posibles = mutableListOf<String>()
        return posibles
    }

    /**
     * Funcion que devuelve las posiciones posibles de un caballo
     */
    fun posibleMoveCaballo(posicion:String):List<String>{
        val posibles = mutableListOf<String>()
        return posibles
    }

    /**
     * Funcion que devuelve las posiciones posibles de un alfil
     */
    fun posibleMoveAlfil(posicion:String):List<String>{
        val posibles = mutableListOf<String>()
        return posibles
    }

    /**
     * Funcion que devuelve las posiciones posibles de una reina
     */
    fun posibleMoveReina(posicion:String):List<String>{
        val posibles = mutableListOf<String>()
        return posibles
    }

    /**
     * Funcion que devuelve las posiciones posibles de un rey
     */
    fun posibleMoveRey(posicion:String):List<String>{
        val posibles = mutableListOf<String>()
        return posibles
    }

    /**
     * Funcion que devuelve las posiciones posibles de un peon
     */
    fun posibleMovePeon(posicion:String):List<String>{
        val posibles = mutableListOf<String>()
        return posibles
    }




}