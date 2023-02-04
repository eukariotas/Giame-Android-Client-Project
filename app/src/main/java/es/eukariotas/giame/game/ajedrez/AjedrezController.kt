package es.eukariotas.giame.game.ajedrez

import android.widget.Toast
import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.utils.viewport.FitViewport
import com.google.android.material.snackbar.Snackbar
import es.eukariotas.giame.game.ajedrez.Object.CasilaAjedrez
import es.eukariotas.giame.game.ajedrez.Object.FichaAjedrez
import kotlin.coroutines.coroutineContext

class AjedrezController: ApplicationAdapter() {
    var tablero = getTableroNuevo()
    lateinit var celdaNegraTexture:Texture
    lateinit var celdaBlancaTexture:Texture
    lateinit var fichaPeonBlancoTexture:Texture
    lateinit var fichaPeonNegroTexture:Texture
    lateinit var fichaTorreBlancaTexture:Texture
    lateinit var fichaTorreNegraTexture:Texture
    lateinit var fichaCaballoBlancoTexture:Texture
    lateinit var fichaCaballoNegroTexture:Texture
    lateinit var fichaAlfilBlancoTexture:Texture
    lateinit var fichaAlfilNegroTexture:Texture
    lateinit var fichaReinaBlancaTexture:Texture
    lateinit var fichaReinaNegraTexture:Texture
    lateinit var fichaReyBlancoTexture:Texture
    lateinit var fichaReyNegroTexture:Texture

    lateinit var batch:SpriteBatch



    /**
     * Función que pintará el tablero de ajedrez
     */
    @Override
    override fun create() {
        tablero = getTableroNuevo()
        celdaBlancaTexture = Texture(Gdx.files.internal("celdaBlanca.png"))
        celdaNegraTexture = Texture(Gdx.files.internal("celdaNegra.png"))
        fichaPeonBlancoTexture = Texture(Gdx.files.internal("peonBlanco.png"))
        fichaPeonNegroTexture = Texture(Gdx.files.internal("peonNegro.png"))
        fichaTorreBlancaTexture = Texture(Gdx.files.internal("torreBlanca.png"))
        fichaTorreNegraTexture = Texture(Gdx.files.internal("TorreNegra.png"))
        fichaCaballoBlancoTexture = Texture(Gdx.files.internal("CaballoBlanco.png"))
        fichaCaballoNegroTexture = Texture(Gdx.files.internal("caballoNegro.png"))
        fichaAlfilBlancoTexture = Texture(Gdx.files.internal("AlfilBlaco.png"))
        fichaAlfilNegroTexture = Texture(Gdx.files.internal("alfilNegro.png"))
        fichaReinaBlancaTexture = Texture(Gdx.files.internal("reinaBlanca.png"))
        fichaReinaNegraTexture = Texture(Gdx.files.internal("reinaNegra.png"))
        fichaReyBlancoTexture = Texture(Gdx.files.internal("reyBlanco.png"))
        fichaReyNegroTexture = Texture(Gdx.files.internal("reiNegro.png"))

        batch = SpriteBatch()



    }
    companion object{
        var turno = 0
        var someMove = true
        var spriteList = mutableListOf<Sprite>()
        fun vaciarListaSprites(){
            spriteList.clear()
        }
        fun incrementarTurno(){
            turno++
        }
        fun hasSomeMove(){
            someMove = true
        }
    }

    override fun render() {
        if (someMove){
        spriteList.clear()
        batch.begin()
        var tamañoCelda = Gdx.graphics.width.toFloat()/8
        for (letra in 1..8){
            for (i in 0..7){
                val posicionX = (letra-1)*tamañoCelda
                val posicionY = i*tamañoCelda
                val letraLetra = getColumna(letra)
                val poscion = "$letraLetra-${i+1}"
                val celda = tablero.keys.find { it.posicion == poscion }!!

                if (celda.color == "negra"){
                    println("negra $poscion $posicionX $posicionY")
                    batch.draw(celdaNegraTexture,posicionX,posicionY,tamañoCelda,tamañoCelda)
                }else{
                    println("blanca $poscion $posicionX $posicionY")
                    batch.draw(celdaBlancaTexture,posicionX,posicionY,tamañoCelda,tamañoCelda)
                }
                if (tablero.get(celda) != null) {
                    pintarFicha(tablero.get(celda)!!,posicionX,posicionY,tamañoCelda)
                }
            }
        }
            someMove = false
            batch.end()
        }

        if (Gdx.input.justTouched()) {
            println("Tocado")
        }
    }

    /**
     * Función que pintará la ficha en la posición indicada
     */
    fun pintarFicha(ficha:FichaAjedrez, posicionX: Float, posicionY: Float, tamañoCelda: Float){
        if (ficha.color == "blanco"){
            if (ficha.tipo == "peon"){
                val sprite = Sprite(fichaPeonBlancoTexture)
                spriteList.add(sprite)
                batch.draw(sprite,posicionX,posicionY,tamañoCelda,tamañoCelda)
            }
            if (ficha.tipo == "torre"){
                val sprite = Sprite(fichaTorreBlancaTexture)
                spriteList.add(sprite)
                batch.draw(sprite,posicionX,posicionY,tamañoCelda,tamañoCelda)
            }
            if (ficha.tipo == "caballo"){
                val sprite = Sprite(fichaCaballoBlancoTexture)
                spriteList.add(sprite)
                batch.draw(sprite,posicionX,posicionY,tamañoCelda,tamañoCelda)
            }
            if (ficha.tipo == "alfil"){
                val sprite = Sprite(fichaAlfilBlancoTexture)
                spriteList.add(sprite)
                batch.draw(sprite,posicionX,posicionY,tamañoCelda,tamañoCelda)
            }
            if (ficha.tipo == "reina"){
                val sprite = Sprite(fichaReinaBlancaTexture)
                spriteList.add(sprite)
                batch.draw(sprite,posicionX,posicionY,tamañoCelda,tamañoCelda)
            }
            if (ficha.tipo == "rey"){
                val sprite = Sprite(fichaReyBlancoTexture)
                spriteList.add(sprite)
                batch.draw(sprite,posicionX,posicionY,tamañoCelda,tamañoCelda)
            }
        }else{
            if (ficha.tipo == "peon"){
                batch.draw(fichaPeonNegroTexture,posicionX,posicionY,tamañoCelda,tamañoCelda)
            }
            if (ficha.tipo == "torre"){
                batch.draw(fichaTorreNegraTexture,posicionX,posicionY,tamañoCelda,tamañoCelda)
            }
            if (ficha.tipo == "caballo"){
                batch.draw(fichaCaballoNegroTexture,posicionX,posicionY,tamañoCelda,tamañoCelda)
            }
            if (ficha.tipo == "alfil"){
                batch.draw(fichaAlfilNegroTexture,posicionX,posicionY,tamañoCelda,tamañoCelda)
            }
            if (ficha.tipo == "reina"){
                batch.draw(fichaReinaNegraTexture,posicionX,posicionY,tamañoCelda,tamañoCelda)
            }
            if (ficha.tipo == "rey"){
                batch.draw(fichaReyNegroTexture,posicionX,posicionY,tamañoCelda,tamañoCelda)
            }
        }
    }


    override fun dispose() {
        celdaBlancaTexture.dispose()
        celdaNegraTexture.dispose()
        fichaPeonBlancoTexture.dispose()
        fichaPeonNegroTexture.dispose()
        fichaTorreBlancaTexture.dispose()
        fichaTorreNegraTexture.dispose()
        fichaCaballoBlancoTexture.dispose()
        fichaCaballoNegroTexture.dispose()
        fichaAlfilBlancoTexture.dispose()
        fichaAlfilNegroTexture.dispose()
        fichaReinaBlancaTexture.dispose()
        fichaReinaNegraTexture.dispose()
        fichaReyBlancoTexture.dispose()
        fichaReyNegroTexture.dispose()

        batch.dispose()
    }

    /**
     * Funcion que devuelve un tablero nuevo
     */
    fun getTableroNuevo(): Map<CasilaAjedrez,FichaAjedrez> {
        var tablerito = mutableMapOf<CasilaAjedrez,FichaAjedrez>()
        //fila 1
        tablerito.put(CasilaAjedrez("negra","a-1"),FichaAjedrez("blanco","torre"))
        tablerito.put(CasilaAjedrez("blanco","b-1"),FichaAjedrez("blanco","caballo"))
        tablerito.put(CasilaAjedrez("negra","c-1"),FichaAjedrez("blanco","alfil"))
        tablerito.put(CasilaAjedrez("blanco","d-1"),FichaAjedrez("blanco","reina"))
        tablerito.put(CasilaAjedrez("negra","e-1"),FichaAjedrez("blanco","rey"))
        tablerito.put(CasilaAjedrez("blanco","f-1"),FichaAjedrez("blanco","alfil"))
        tablerito.put(CasilaAjedrez("negra","g-1"),FichaAjedrez("blanco","caballo"))
        tablerito.put(CasilaAjedrez("blanco","h-1"),FichaAjedrez("blanco","torre"))
        //fila 2
        tablerito.put(CasilaAjedrez("blanco","a-2"),FichaAjedrez("blanco","peon"))
        tablerito.put(CasilaAjedrez("negra","b-2"),FichaAjedrez("blanco","peon"))
        tablerito.put(CasilaAjedrez("blanco","c-2"),FichaAjedrez("blanco","peon"))
        tablerito.put(CasilaAjedrez("negra","d-2"),FichaAjedrez("blanco","peon"))
        tablerito.put(CasilaAjedrez("blanco","e-2"),FichaAjedrez("blanco","peon"))
        tablerito.put(CasilaAjedrez("negra","f-2"),FichaAjedrez("blanco","peon"))
        tablerito.put(CasilaAjedrez("blanco","g-2"),FichaAjedrez("blanco","peon"))
        tablerito.put(CasilaAjedrez("negra","h-2"),FichaAjedrez("blanco","peon"))
        //fila 8
        tablerito.put(CasilaAjedrez("blanco","a-8"),FichaAjedrez("negro","torre"))
        tablerito.put(CasilaAjedrez("negra","b-8"),FichaAjedrez("negro","caballo"))
        tablerito.put(CasilaAjedrez("blanco","c-8"),FichaAjedrez("negro","alfil"))
        tablerito.put(CasilaAjedrez("negra","d-8"),FichaAjedrez("negro","reina"))
        tablerito.put(CasilaAjedrez("blanco","e-8"),FichaAjedrez("negro","rey"))
        tablerito.put(CasilaAjedrez("negra","f-8"),FichaAjedrez("negro","alfil"))
        tablerito.put(CasilaAjedrez("blanco","g-8"),FichaAjedrez("negro","caballo"))
        tablerito.put(CasilaAjedrez("negra","h-8"),FichaAjedrez("negro","torre"))
        //fila 7
        tablerito.put(CasilaAjedrez("negra","a-7"),FichaAjedrez("negro","peon"))
        tablerito.put(CasilaAjedrez("blanco","b-7"),FichaAjedrez("negro","peon"))
        tablerito.put(CasilaAjedrez("negra","c-7"),FichaAjedrez("negro","peon"))
        tablerito.put(CasilaAjedrez("blanco","d-7"),FichaAjedrez("negro","peon"))
        tablerito.put(CasilaAjedrez("negra","e-7"),FichaAjedrez("negro","peon"))
        tablerito.put(CasilaAjedrez("blanco","f-7"),FichaAjedrez("negro","peon"))
        tablerito.put(CasilaAjedrez("negra","g-7"),FichaAjedrez("negro","peon"))
        tablerito.put(CasilaAjedrez("blanco","h-7"),FichaAjedrez("negro","peon"))

        for (fila in 3..6) {
            for (columna in 1..8) {
                val color = if ((fila + columna) % 2 == 0) "negra" else "blanco"
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