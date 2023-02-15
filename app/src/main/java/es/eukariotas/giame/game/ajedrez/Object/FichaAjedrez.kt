package es.eukariotas.giame.game.ajedrez.Object

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Sprite

data class FichaAjedrez(
    val color: String,
    val tipo: String,
):Sprite() {

    companion object{
        val letras = listOf("a","b","c","d","e","f","g","h")
    }
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
                else -> return listOf()//alguna movida de error
            }

    }

    /**
     * Funcion que devuelve las posiciones posibles de una torre
     */
    fun posibleMoveTorre(posicion:String):List<String>{
        val posibles = mutableListOf<String>()
        val pos = posicion.split("-")
        val letra = pos[0]
        val numero = pos[1].toInt()
        for (i in 1..8){
            if (i != numero){
                posibles.add("$letra-$i")
            }
        }
        for (i in letras){
            if (i != letra){
                posibles.add("$i-$numero")
            }
        }
        return posibles
    }

    /**
     * Funcion que devuelve las posiciones posibles de un caballo
     */
    fun posibleMoveCaballo(posicion:String):List<String>{
        val posibles = mutableListOf<String>()
        val pos = posicion.split("-")
        val letra = pos[0]
        val posiocionLetra = letras.indexOf(letra)
        val numero = pos[1].toInt()
        for (i in numero-2..numero+2){
            if (i != numero){
                if (i in 1..8){
                    if (i == numero-2 || i == numero+2){
                        if (posiocionLetra-1 >= 0){
                            posibles.add("${letras[posiocionLetra-1]}-$i")
                        }
                        if (posiocionLetra+1 <= 7){
                            posibles.add("${letras[posiocionLetra+1]}-$i")
                        }
                }else{
                        if (posiocionLetra-2 >= 0){
                            posibles.add("${letras[posiocionLetra-2]}-$i")
                        }
                        if (posiocionLetra+2 <= 7){
                            posibles.add("${letras[posiocionLetra+2]}-$i")
                        }
                    }
                }
            }
        }
        return posibles
    }

    /**
     * Funcion que devuelve las posiciones posibles de un alfil
     */
    fun posibleMoveAlfil(posicion:String):List<String>{
        val posibles = mutableListOf<String>()
        val pos = posicion.split("-")
        val letra = pos[0]
        val posicionLetra = letras.indexOf(letra)
        val numero = pos[1].toInt()
        if (numero-1 >= 1){
            var contador = 0
            for (i in numero-1 .. 1){
                contador++
                if (posicionLetra-contador >= 0){
                    posibles.add("${letras[posicionLetra-contador]}-$i")
                }
                if (posicionLetra+contador <= 7){
                    posibles.add("${letras[posicionLetra+contador]}-$i")
                }
            }
        }
        if (numero+1 <= 8){
            var contador = 0
           for (i in numero+1 .. 8){
                contador++
                if (posicionLetra-contador >= 0){
                     posibles.add("${letras[posicionLetra-contador]}-$i")
                }
                if (posicionLetra+contador <= 7){
                     posibles.add("${letras[posicionLetra+contador]}-$i")
                }
           }
        }

        return posibles
    }

    /**
     * Funcion que devuelve las posiciones posibles de una reina
     */
    fun posibleMoveReina(posicion:String):List<String>{
        val posibles = mutableListOf<String>()
         posibles.addAll(posibleMoveTorre(posicion))
         posibles.addAll(posibleMoveAlfil(posicion))
        return posibles
    }

    /**
     * Funcion que devuelve las posiciones posibles de un rey
     */
    fun posibleMoveRey(posicion:String):List<String>{
        val posibles = mutableListOf<String>()
        val pos = posicion.split("-")
        val letra = pos[0]
        val posicionLetra = letras.indexOf(letra)
        val numero = pos[1].toInt()
        for (i in numero-1..numero+1){
            if (i in 1..8){
            posibles.add("${letras[posicionLetra-1]}-$i")
            if ("${letras[posicionLetra]}-$i" != posicion){
                posibles.add("${letras[posicionLetra]}-$i")
            }
            posibles.add("${letras[posicionLetra+1]}-$i")
        }
        }
        return posibles
    }

    /**
     * Funcion que devuelve las posiciones posibles de un peon
     */
    fun posibleMovePeon(posicion:String):List<String>{
        val posibles = mutableListOf<String>()
        val pos = posicion.split("-")
        val letra = pos[0]
        val numero = pos[1].toInt()
        if (this.color == "blanco"){
            if (numero == 2){
                posibles.add("$letra-${numero+1}")
                posibles.add("$letra-${numero+2}")
            }else{
                posibles.add("$letra-${numero+1}")
            }
        }else{
            if (numero == 7){
                posibles.add("$letra-${numero-1}")
                posibles.add("$letra-${numero-2}")
            }else{
                posibles.add("$letra-${numero-1}")
            }
        }

        return posibles
    }


}