package es.eukariotas.giame.game.ajedrez

import com.badlogic.gdx.ApplicationAdapter
import es.eukariotas.giame.game.ajedrez.Object.FichaAjedrez

class AjedrezController: ApplicationAdapter() {
    val Tablero = getTableroNuevo()





    /**
     * Funcion que devuelve un tablero nuevo
     */
    fun getTableroNuevo(): Map<String,FichaAjedrez> {
        return mapOf(
            "a-1" to FichaAjedrez("blanco", "torre"),
            "b-1" to FichaAjedrez("blanco", "caballo"),
            "c-1" to FichaAjedrez("blanco", "alfil"),
            "d-1" to FichaAjedrez("blanco", "reina"),
            "e-1" to FichaAjedrez("blanco", "rey"),
            "f-1" to FichaAjedrez("blanco", "alfil"),
            "g-1" to FichaAjedrez("blanco", "caballo"),
            "h-1" to FichaAjedrez("blanco", "torre"),
            "a-2" to FichaAjedrez("blanco", "peon"),
            "b-2" to FichaAjedrez("blanco", "peon"),
            "c-2" to FichaAjedrez("blanco", "peon"),
            "d-2" to FichaAjedrez("blanco", "peon"),
            "e-2" to FichaAjedrez("blanco", "peon"),
            "f-2" to FichaAjedrez("blanco", "peon"),
            "g-2" to FichaAjedrez("blanco", "peon"),
            "h-2" to FichaAjedrez("blanco", "peon"),
            "a-8" to FichaAjedrez("negro", "torre"),
            "b-8" to FichaAjedrez("negro", "caballo"),
            "c-8" to FichaAjedrez("negro", "alfil"),
            "d-8" to FichaAjedrez("negro", "reina"),
            "e-8" to FichaAjedrez("negro", "rey"),
            "f-8" to FichaAjedrez("negro", "alfil"),
            "g-8" to FichaAjedrez("negro", "caballo"),
            "h-8" to FichaAjedrez("negro", "torre"),
            "a-7" to FichaAjedrez("negro", "peon"),
            "b-7" to FichaAjedrez("negro", "peon"),
            "c-7" to FichaAjedrez("negro", "peon"),
            "d-7" to FichaAjedrez("negro", "peon"),
            "e-7" to FichaAjedrez("negro", "peon"),
            "f-7" to FichaAjedrez("negro", "peon"),
            "g-7" to FichaAjedrez("negro", "peon"),
            "h-7" to FichaAjedrez("negro", "peon"),
        )
    }

    fun isEmpy(posicion:String):Boolean{
        //TODO(Devuelve si esta vacia la posicion indicada)
        return true
    }

    fun isEnemy(posicion:String):Boolean{
        //TODO(Devuelve si hay una ficha enemiga en la posicion indicada)
        return true
    }

}