package es.eukariotas.giame.game.ajedrez

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.InputAdapter
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import es.eukariotas.giame.game.ajedrez.Object.CasilaAjedrez
import es.eukariotas.giame.game.ajedrez.Object.FichaAjedrez

class AjedrezController: ApplicationAdapter() {
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
    lateinit var celdaVerdeBlanca:Texture
    lateinit var celdaVerdeNegra:Texture
    private var camera: OrthographicCamera? = null
    lateinit var batch:SpriteBatch



    /**
     * Función que pintará el tablero de ajedrez
     */
    @Override
    override fun create() {
        tamañoCelda = Gdx.graphics.width.toFloat() / 8
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
        celdaVerdeBlanca = Texture(Gdx.files.internal("celdaVerdeBlanca.png"))
        celdaVerdeNegra = Texture(Gdx.files.internal("celdaVerdeNegra.png"))
        camera = OrthographicCamera()
        camera!!.setToOrtho(false, Gdx.graphics.width.toFloat(), Gdx.graphics.height.toFloat())
        batch = SpriteBatch()
        getTableroNuevo(tamañoCelda)
        Gdx.input.inputProcessor = object : InputAdapter(){
            var posiblesPosiciones:List<String> = ArrayList()
        override fun touchDown(screenX: Int, screenY: Int, pointer: Int, button: Int): Boolean {
            val x = Gdx.input.x
            val y = (Gdx.input.y-Gdx.graphics.height)*-1
            println("tocado en ${x} ${y}")
            var ficha = false
            for(sprite in spriteList){
                if (sprite.boundingRectangle.contains(x.toFloat(),y.toFloat())){
                    ficha = true

                    for (fichas in fichas){
                        if (fichas.value.boundingRectangle.contains(x.toFloat(),y.toFloat())){
                            var ficha = fichas.value
                            var posicion = fichas.key
                            posiblesPosiciones = comprobarCasillas(ficha,posicion)
                            println("Ficha ${ficha.tipo} ${ficha.color}")
                            for (posicion in posiblesPosiciones){
                                changeCeldaColor("verde",posicion)
                            }

                        }
                    }
                }
            }
            if (!ficha){
                println("Tocada una celda")
            }
          return true
        }

        override fun touchUp(screenX: Int, screenY: Int, pointer: Int, button: Int): Boolean {
            println("soltada")
            for (posicion in posiblesPosiciones){
                changeCeldaColor("normal",posicion)
            }
            return true
        }

        }



    }
    companion object{
        var tamañoCelda = 0f
        var turno = 0
        var someMove = true
        var spriteList = mutableListOf<Sprite>()
        var tablero = mutableMapOf<String, CasilaAjedrez>()
        var fichas = mutableMapOf<String, FichaAjedrez>()
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
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        printTablero(tamañoCelda)

    }

    /**
     * Función que pintará la ficha en la posición indicada
     */
    fun pintarFicha(ficha:FichaAjedrez, posicionX: Float, posicionY: Float, tamañoCelda: Float){
        var texture:Texture? = null
        if (ficha.color == "blanco"){
            if (ficha.tipo == "peon"){
                texture = fichaPeonBlancoTexture
                var sprite = Sprite(texture)
                sprite.setPosition(posicionX,posicionY)
                sprite.setSize(tamañoCelda,tamañoCelda)
                spriteList.add(sprite)
                batch.draw(sprite,posicionX,posicionY,tamañoCelda,tamañoCelda)
            }
            if (ficha.tipo == "torre"){
                texture = fichaTorreBlancaTexture
                var sprite = Sprite(texture)
                sprite.setPosition(posicionX,posicionY)
                sprite.setSize(tamañoCelda,tamañoCelda)
                spriteList.add(sprite)
                batch.draw(sprite,posicionX,posicionY,tamañoCelda,tamañoCelda)
            }
            if (ficha.tipo == "caballo"){
                texture = fichaCaballoBlancoTexture
                var sprite = Sprite(texture)
                sprite.setPosition(posicionX,posicionY)
                sprite.setSize(tamañoCelda,tamañoCelda)
                spriteList.add(sprite)
                batch.draw(sprite,posicionX,posicionY,tamañoCelda,tamañoCelda)
            }
            if (ficha.tipo == "alfil"){
                texture = fichaAlfilBlancoTexture
                var sprite = Sprite(texture)
                sprite.setPosition(posicionX,posicionY)
                sprite.setSize(tamañoCelda,tamañoCelda)
                spriteList.add(sprite)
                batch.draw(sprite,posicionX,posicionY,tamañoCelda,tamañoCelda)
            }
            if (ficha.tipo == "reina"){
                texture = fichaReinaBlancaTexture
                var sprite = Sprite(texture)
                sprite.setPosition(posicionX,posicionY)
                sprite.setSize(tamañoCelda,tamañoCelda)
                spriteList.add(sprite)
                batch.draw(sprite,posicionX,posicionY,tamañoCelda,tamañoCelda)
            }
            if (ficha.tipo == "rey"){
                texture = fichaReyBlancoTexture
                var sprite = Sprite(texture)
                sprite.setPosition(posicionX,posicionY)
                sprite.setSize(tamañoCelda,tamañoCelda)
                spriteList.add(sprite)
                batch.draw(sprite,posicionX,posicionY,tamañoCelda,tamañoCelda)
            }
        }else{
            if (ficha.tipo == "peon"){
                var sprite = Sprite(fichaPeonNegroTexture)
                sprite.setPosition(posicionX,posicionY)
                sprite.setSize(tamañoCelda,tamañoCelda)
                spriteList.add(sprite)
                batch.draw(sprite,posicionX,posicionY,tamañoCelda,tamañoCelda)
            }
            if (ficha.tipo == "torre"){
                var sprite = Sprite(fichaTorreNegraTexture)
                sprite.setPosition(posicionX,posicionY)
                sprite.setSize(tamañoCelda,tamañoCelda)
                spriteList.add(sprite)
                batch.draw(sprite,posicionX,posicionY,tamañoCelda,tamañoCelda)
            }
            if (ficha.tipo == "caballo"){
                var sprite = Sprite(fichaCaballoNegroTexture)
                sprite.setPosition(posicionX,posicionY)
                sprite.setSize(tamañoCelda,tamañoCelda)
                spriteList.add(sprite)
                batch.draw(sprite,posicionX,posicionY,tamañoCelda,tamañoCelda)
            }
            if (ficha.tipo == "alfil"){
                var sprite = Sprite(fichaAlfilNegroTexture)
                sprite.setPosition(posicionX,posicionY)
                sprite.setSize(tamañoCelda,tamañoCelda)
                spriteList.add(sprite)
                batch.draw(sprite,posicionX,posicionY,tamañoCelda,tamañoCelda)
            }
            if (ficha.tipo == "reina"){
                var sprite = Sprite(fichaReinaNegraTexture)
                sprite.setPosition(posicionX,posicionY)
                sprite.setSize(tamañoCelda,tamañoCelda)
                spriteList.add(sprite)
                batch.draw(sprite,posicionX,posicionY,tamañoCelda,tamañoCelda)
            }
            if (ficha.tipo == "rey"){
                var sprite = Sprite(fichaReyNegroTexture)
                sprite.setPosition(posicionX,posicionY)
                sprite.setSize(tamañoCelda,tamañoCelda)
                spriteList.add(sprite)
                batch.draw(sprite,posicionX,posicionY,tamañoCelda,tamañoCelda)
            }
        }


    }


    override fun dispose() {
        celdaBlancaTexture.dispose()
        celdaNegraTexture.dispose()
        celdaVerdeNegra.dispose()
        celdaVerdeBlanca.dispose()
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

        batch!!.dispose()
    }

    /**
     * Funcion que devuelve un tablero nuevo
     */
    fun getTableroNuevo(tamañoCelda: Float) {
        var texture: Texture? = null
        for(linea in 1..8){
            for (letra in 1..8){
                var posicion = "${getColumna(letra)}-${linea}"
                val posicionX = (letra - 1) * tamañoCelda
                val posicionY = (linea - 1) * tamañoCelda
                val color = if ((linea + letra) % 2 == 1) "negra" else "blanco"
                if (color == "blanco"){
                    texture = celdaBlancaTexture
                }else{
                    texture = celdaNegraTexture
                }
                println("celda posicion:${posicion} color: ${color}")
                val fichaAjedrez = getFicha(posicion)
                var casillaAjedrez = CasilaAjedrez(color,posicion)
                fichaAjedrez.setSize(tamañoCelda,tamañoCelda)
                casillaAjedrez.setSize(tamañoCelda,tamañoCelda)
                casillaAjedrez.setPosition(posicionX,posicionY)
                fichaAjedrez.setPosition(posicionX,posicionY)
                casillaAjedrez.texture = texture
                tablero.put(posicion,casillaAjedrez)
                if (fichaAjedrez.color != "vacía"){
                    fichas.put(posicion,fichaAjedrez)
                }
            }
        }
        println("total de fichas: ${fichas.size}")

    }
    fun comprobarCasillas(ficha: FichaAjedrez, posicion: String): List<String>{
        val posiciones = ficha.getPosiblePosition(posicion)
        var posicionesValidas = mutableListOf<String>()
        for (posicion in posiciones){
            if (!fichas.containsKey(posicion)){
                posicionesValidas.add(posicion)
                    }
                }

        return posicionesValidas
    }
    fun getFicha(posicion: String): FichaAjedrez {
        var posiciones = posicion.split("-")
        if (posiciones[1]=="2"){
            var ficha = FichaAjedrez("blanco","peon")
            ficha.texture = fichaPeonBlancoTexture
            return ficha
        }
        if (posiciones[1]=="7"){
            var ficha = FichaAjedrez("negro","peon")
            ficha.texture = fichaPeonNegroTexture
            return ficha

        }
        if (posiciones[1]=="1"){
            if (posiciones[0]=="a"){
                var ficha = FichaAjedrez("blanco","torre")
                ficha.texture = fichaTorreBlancaTexture
                return ficha

            }
            if (posiciones[0]=="b"){
                var ficha = FichaAjedrez("blanco","caballo")
                ficha.texture = fichaCaballoBlancoTexture
                return ficha

            }
            if (posiciones[0]=="c"){
                var ficha = FichaAjedrez("blanco","alfil")
                ficha.texture = fichaAlfilBlancoTexture
                return ficha

            }
            if (posiciones[0]=="d"){
                var ficha = FichaAjedrez("blanco","reina")
                ficha.texture = fichaReinaBlancaTexture
                return ficha
            }
            if (posiciones[0]=="e"){
                var ficha = FichaAjedrez("blanco","rey")
                ficha.texture = fichaReyBlancoTexture
                return ficha
            }
            if (posiciones[0]=="f"){
                var ficha = FichaAjedrez("blanco","alfil")
                ficha.texture = fichaAlfilBlancoTexture
                return ficha
            }
            if (posiciones[0]=="g"){
                var ficha = FichaAjedrez("blanco","caballo")
                ficha.texture = fichaCaballoBlancoTexture
                return ficha
            }
            if (posiciones[0]=="h"){
                var ficha = FichaAjedrez("blanco","torre")
                ficha.texture = fichaTorreBlancaTexture
                return ficha
            }
        }
        if(posiciones[1]=="8"){
            if (posiciones[0]=="a"){
                var ficha = FichaAjedrez("negro","torre")
                ficha.texture = fichaTorreNegraTexture
                return ficha
            }
            if (posiciones[0]=="b"){
                var ficha = FichaAjedrez("negro","caballo")
                ficha.texture = fichaCaballoNegroTexture
                return ficha
            }
            if (posiciones[0]=="c"){
            var ficha = FichaAjedrez("negro","alfil")
            ficha.texture = fichaAlfilNegroTexture
            return ficha
            }
            if (posiciones[0]=="d"){
                var ficha = FichaAjedrez("negro","reina")
                ficha.texture = fichaReinaNegraTexture
                return ficha
            }
            if (posiciones[0]=="e"){
                var ficha = FichaAjedrez("negro","rey")
                ficha.texture = fichaReyNegroTexture
                return ficha
            }
            if (posiciones[0]=="f"){
                var ficha = FichaAjedrez("negro","alfil")
                ficha.texture = fichaAlfilNegroTexture
                return ficha
            }
            if (posiciones[0]=="g"){
                var ficha = FichaAjedrez("negro","caballo")
                ficha.texture = fichaCaballoNegroTexture
                return ficha
            }
            if (posiciones[0]=="h"){
                var ficha = FichaAjedrez("negro","torre")
                ficha.texture = fichaTorreNegraTexture
                return ficha
            }
        }
        return FichaAjedrez("vacía","n/a")
    }
    fun printTablero(tamañoCelda: Float) {
        spriteList.clear()
        batch.begin()

        for (posicion in tablero.keys) {
            val casilla = tablero.get(posicion)
            casilla!!.draw(batch)
           }
        for (ficha in fichas.values) {
            pintarFicha(ficha,ficha.x,ficha.y,tamañoCelda)
        }


        batch.end()

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
        for (i in fichas){
            if (i.key==posicion){
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
              for (i in fichas) {
                if (i.key == posicion) {
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

    fun changeCeldaColor(color: String, posicion: String) {
        var casilla = tablero.get(posicion)
        if (casilla == null) {
            throw Exception("No existe esa posicion")
        }
        when(color){
            "verde" -> if (casilla!!.color == "blanco") {
                casilla!!.texture = celdaVerdeBlanca
            } else {
                casilla!!.texture = celdaVerdeNegra
            }
            "normal" -> if (casilla!!.color == "blanco") {
                casilla!!.texture = celdaBlancaTexture
            } else {
                casilla!!.texture = celdaNegraTexture
            }
        }
        tablero.put(posicion, casilla)
    }

}