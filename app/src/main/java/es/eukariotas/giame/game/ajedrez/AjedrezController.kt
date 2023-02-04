package es.eukariotas.giame.game.ajedrez

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import es.eukariotas.giame.game.ajedrez.Object.CasilaAjedrez
import es.eukariotas.giame.game.ajedrez.Object.FichaAjedrez

class AjedrezController: ApplicationAdapter() {
    val tablero = getTableroNuevo()
    lateinit var celdaNegraTexture:Texture
    lateinit var celdaBlancaTexture:Texture
    lateinit var batch: SpriteBatch
    /**
     * Función que pintará el tablero de ajedrez
     */
    @Override
    override fun create() {
        celdaBlancaTexture = Texture(Gdx.files.internal("celdaBlanca.png"))
        celdaNegraTexture = Texture(Gdx.files.internal("celdaNegra.png"))
        batch = SpriteBatch()
    }

    override fun render() {
        batch.begin()
        for (casilla in tablero.keys) {
            if (casilla.color == "negra") {
                batch.draw(celdaNegraTexture, 0f, 0f)
            } else {
                batch.draw(celdaBlancaTexture, 50f, 50f)
            }
        }
        batch.end()
    }
    override fun dispose() {
        celdaBlancaTexture.dispose()
        celdaNegraTexture.dispose()
    }

    /**
     * Funcion que devuelve un tablero nuevo
     */
    fun getTableroNuevo(): Map<CasilaAjedrez,FichaAjedrez> {
        var tablerito = mutableMapOf<CasilaAjedrez,FichaAjedrez>()
        tablerito.put(CasilaAjedrez("negra","a-1"),FichaAjedrez("blanco","torre"))
        tablerito.put(CasilaAjedrez("blanco","b-1"),FichaAjedrez("blanco","caballo"))
        tablerito.put(CasilaAjedrez("negra","c-1"),FichaAjedrez("blanco","alfil"))
        tablerito.put(CasilaAjedrez("blanco","d-1"),FichaAjedrez("blanco","reina"))
        tablerito.put(CasilaAjedrez("negra","e-1"),FichaAjedrez("blanco","rey"))
        tablerito.put(CasilaAjedrez("blanco","f-1"),FichaAjedrez("blanco","alfil"))
        tablerito.put(CasilaAjedrez("negra","g-1"),FichaAjedrez("blanco","caballo"))
        tablerito.put(CasilaAjedrez("blanco","h-1"),FichaAjedrez("blanco","torre"))
        tablerito.put(CasilaAjedrez("negra","a-2"),FichaAjedrez("blanco","peón"))
        tablerito.put(CasilaAjedrez("blanco","b-2"),FichaAjedrez("blanco","peón"))
        tablerito.put(CasilaAjedrez("negra","c-2"),FichaAjedrez("blanco","peón"))
        tablerito.put(CasilaAjedrez("blanco","d-2"),FichaAjedrez("blanco","peón"))
        tablerito.put(CasilaAjedrez("negra","e-2"),FichaAjedrez("blanco","peón"))
        tablerito.put(CasilaAjedrez("blanco","f-2"),FichaAjedrez("blanco","peón"))
        tablerito.put(CasilaAjedrez("negra","g-2"),FichaAjedrez("blanco","peón"))
        tablerito.put(CasilaAjedrez("blanco","h-2"),FichaAjedrez("blanco","peón"))
        tablerito.put(CasilaAjedrez("blanco","a-8"),FichaAjedrez("negro","torre"))
        tablerito.put(CasilaAjedrez("negra","b-8"),FichaAjedrez("negro","caballo"))
        tablerito.put(CasilaAjedrez("blanco","c-8"),FichaAjedrez("negro","alfil"))
        tablerito.put(CasilaAjedrez("negra","d-8"),FichaAjedrez("negro","reina"))
        tablerito.put(CasilaAjedrez("blanco","e-8"),FichaAjedrez("negro","rey"))
        tablerito.put(CasilaAjedrez("negra","f-8"),FichaAjedrez("negro","alfil"))
        tablerito.put(CasilaAjedrez("blanco","g-8"),FichaAjedrez("negro","caballo"))
        tablerito.put(CasilaAjedrez("negra","h-8"),FichaAjedrez("negro","torre"))
        tablerito.put(CasilaAjedrez("blanco","a-7"),FichaAjedrez("negro","peón"))
        tablerito.put(CasilaAjedrez("negra","b-7"),FichaAjedrez("negro","peón"))
        tablerito.put(CasilaAjedrez("blanco","c-7"),FichaAjedrez("negro","peón"))
        tablerito.put(CasilaAjedrez("negra","d-7"),FichaAjedrez("negro","peón"))
        tablerito.put(CasilaAjedrez("blanco","e-7"),FichaAjedrez("negro","peón"))
        tablerito.put(CasilaAjedrez("negra","f-7"),FichaAjedrez("negro","peón"))
        tablerito.put(CasilaAjedrez("blanco","g-7"),FichaAjedrez("negro","peón"))
        tablerito.put(CasilaAjedrez("negra","h-7"),FichaAjedrez("negro","peón"))

        for (fila in 3..6) {
            for (columna in 1..8) {
                val color = if ((fila + columna) % 2 == 0) "blanco" else "negra"
                val celda = "${getColumna(columna)}-$fila"
                tablerito.put(CasilaAjedrez(color, celda), FichaAjedrez("vacía", "n/a"))
            }
        }
        return tablerito
    }

    /**
     * Funcion que devuelve el tablero actual
     * Para testeo
     */
    fun mostrarTablero() {
        for(i in 1..8) {
            for(j in 1..8) {
                val celda = "${getColumna(j)}-$i"
                tablero.entries.forEach {
                    if(it.key.posicion == celda) {
                        print("${celda} ${it.value.color} ${it.value.tipo} | ")
                    }
                }
            }
            println()
        }
    }

    /**
     * Asigna un valor numerico a cada columna
     * Esto facilita el recorrido del tablero
     */
    fun getColumna(columna: Int) : String {
        return when(columna) {
            1 -> "a"
            2 -> "b"
            3 -> "c"
            4 -> "d"
            5 -> "e"
            6 -> "f"
            7 -> "g"
            8 -> "h"
            else -> "n/a"
        }
    }


    /**
     * Detecta si la casilla de destino esta ocupada
     */
    fun isEmpy(posicion:String):Boolean{
        for (i in tablero){
            if (i.key.posicion==posicion){
                if (i.value.tipo=="n/a"){
                    return true
                }else{
                    return false
                }
            }
        }
        throw Exception("No existe esa posicion")
    }

    /**
     * Detecta si la casilla de destino esta ocupada por una ficha del adversario
     */
    fun isEnemy(posicion:String, enemyColor:String):Boolean{
       if(isEmpy(posicion)){
           return false
       }else {
              for (i in tablero) {
                if (i.key.posicion == posicion) {
                     if (i.value.color == enemyColor) {
                          return true
                     } else {
                          return false
                     }
                }
              }
       }
        throw Exception("Error al detectar enemigo")
    }

    /**
     * Verifica si la casilla de destino esta dentro del tablero
     */
    fun verifyMovimiento(posicion:String):Boolean{
        return isEmpy(posicion)
    }

    fun verifyMovimientoList(posiciones:List<String>):List<String>{
        var posicionesValidas:List<String> = listOf()
        for (i in posiciones){
            if (verifyMovimiento(i)){
                posicionesValidas.plus(i)
            }
        }
        return posicionesValidas
    }


}