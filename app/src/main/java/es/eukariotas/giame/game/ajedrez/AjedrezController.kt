package es.eukariotas.giame.game.ajedrez

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.InputAdapter
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop
import es.eukariotas.giame.game.ajedrez.Object.CasilaAjedrez
import es.eukariotas.giame.game.ajedrez.Object.FichaAjedrez

class AjedrezController: ApplicationAdapter() {
    //las distintas texturas que se van a usar
    lateinit var texturaLadrillo: Texture
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
     * Al iniciar se cargan las texturas
     * y se realizan las configuraciones iniciales
     */
    @Override
    override fun create() {
        tamañoCelda = Gdx.graphics.width.toFloat() / 8//se calcula el tamaño de las celdas
        //texturas
        texturaLadrillo = Texture(Gdx.files.internal("wallPrint.png"))
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
        //se crea la camara
        camera = OrthographicCamera()
        camera!!.setToOrtho(false, Gdx.graphics.width.toFloat(), Gdx.graphics.height.toFloat())
        batch = SpriteBatch()
        //se crea el tablero con las fichas
        getTableroNuevo(tamañoCelda)

        //el input processor se encarga de los eventos de entrada
        Gdx.input.inputProcessor = object : InputAdapter(){
            var posiblesPosiciones:List<String> = ArrayList()//lista de posiciones posibles
            var fichaSeleccionada:FichaAjedrez? = null//ficha seleccionada
            var posicionFichaSeleccionada:String? = null//posicion de la ficha seleccionada
            var celdaSeleccionada:CasilaAjedrez? = null//celda seleccionada

        override fun touchDown(screenX: Int, screenY: Int, pointer: Int, button: Int): Boolean {
            //las posiciones pintadas de verde se ponen de nuevo en su color
            if (posiblesPosiciones != null){
                for (posicion in posiblesPosiciones){
                    changeCeldaColor("normal",posicion)
                }
            }

            if (fichaSeleccionada!=null){
                fichas.remove(posicionFichaSeleccionada)
                fichaSeleccionada!!.setSize(tamañoCelda,tamañoCelda)
                fichas.put(posicionFichaSeleccionada!!,fichaSeleccionada!!)
            }

            //recogemos la posicion de la casilla que se ha tocado
            val x = Gdx.input.x
            val y = (Gdx.input.y-Gdx.graphics.height)*-1

            println("tocado en ${x} ${y}")//se indica donde se ha tocado

            //detectamos que celda se ha pulsado
            for (celda in tablero){
                if (celda.value.boundingRectangle.contains(x.toFloat(),y.toFloat())&&posiblesPosiciones.contains(celda.key)){
                    celdaSeleccionada = celda.value
                }
            }
            var ficha = false//variable que se usara si se ha tocado una ficha o una celda vacia
            //se comprueba si se ha tocado una ficha
            for(sprite in spriteList){
                if (sprite.boundingRectangle.contains(x.toFloat(),y.toFloat())){
                    ficha = true//se indica que se a pulsado una tecla
                    for (fichaa in fichas){
                        if (fichaa.value.boundingRectangle.contains(x.toFloat(),y.toFloat())){
                            fichaSeleccionada = fichaa.value
                            posicionFichaSeleccionada = fichaa.key

                        }
                    }
                }
            }
            //si no se ha pulsado una ficha se ha pulsado una celda vacia
            if (!ficha){
                //se comprueba si hay posibles posiciones
                if(posiblesPosiciones != null && fichaSeleccionada != null){
                    //se comprueba si la celda pulsada esta en la lista de posibles posiciones
                    if (posiblesPosiciones.contains(celdaSeleccionada!!.posicion)){
                            fichaSeleccionada!!.setSize(tamañoCelda,tamañoCelda)

                            fichas.remove(posicionFichaSeleccionada)

                            fichaSeleccionada!!.setPosition(celdaSeleccionada!!.x,celdaSeleccionada!!.y)
                            fichas.put(getKeyOfCell(celdaSeleccionada!!),fichaSeleccionada!!)
                        }
                }


            }else{
                posiblesPosiciones = comprobarCasillas(fichaSeleccionada!!,posicionFichaSeleccionada!!)
                for (posicion in posiblesPosiciones){
                    changeCeldaColor("verde",posicion)
                }
                fichas.remove(posicionFichaSeleccionada)
                fichaSeleccionada!!.setSize(tamañoCelda-10,tamañoCelda-10)
                fichas.put(posicionFichaSeleccionada!!,fichaSeleccionada!!)
            }
          return true
        }

        }
        posicionInicialY = (Gdx.graphics.height.toFloat()-Gdx.graphics.width.toFloat())
    }

    /**
     *Metodo que realiza las acciones necesarias mover una ficha
     */
    fun moverFicha(ficha:FichaAjedrez,posicion:String){
        var posicionAtual = getKeyOfFicha(ficha)
        var celdaFutura = tablero.get(posicion)

        if (posicionAtual != ""){
            fichas.remove(posicionAtual)
            ficha.setPosition(celdaFutura!!.x,celdaFutura!!.y)
            ficha.setSize(tamañoCelda,tamañoCelda)
            fichas.put(posicion,ficha)
        }
    }

    companion object{
        var posicionInicialY = 0f
        var tamañoCelda = 0f
        var turno = 0
        var someMove = true
        var spriteList = mutableListOf<Sprite>()
        var tablero = mutableMapOf<String, CasilaAjedrez>()
        var fichas = mutableMapOf<String, FichaAjedrez>()
        val posicionPantalla = mutableMapOf<String, Vector2>()//por casa posicion del tablero almacenamos su posicion en la pantalla
        fun vaciarListaSprites(){
            spriteList.clear()
        }
        fun incrementarTurno(){
            turno++
        }

        fun getKeyOfCell(cell:CasilaAjedrez):String{
            for (celda in tablero){
                if (celda.value == cell){
                    return celda.key
                }
            }
            return ""
        }
        fun getKeyOfFicha(ficha:FichaAjedrez):String{
            for (fichaa in fichas){
                if (fichaa.value == ficha){
                    return fichaa.key
                }
            }
            return ""
        }



    }

    override fun render() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        printTablero(tamañoCelda)
        pintarInterfaz()
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
        texturaLadrillo.dispose()
        batch!!.dispose()
    }

    /**
     * Funcion que devuelve un tablero nuevo
     */
    fun getTableroNuevo(tamañoCelda: Float) {
        var texture: Texture? = null
        posicionInicialY = (Gdx.graphics.height.toFloat()-Gdx.graphics.width.toFloat())/2
        for(linea in 1..8){
            for (letra in 1..8){
                var posicion = "${getColumna(letra)}-${linea}"
                val posicionX = (letra - 1) * tamañoCelda
                val posicionY = ((linea - 1) * tamañoCelda)+ posicionInicialY
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
                posicionPantalla.put(posicion, Vector2(posicionX,posicionY))
                if (fichaAjedrez.color != "vacía"){
                    fichas.put(posicion,fichaAjedrez)
                }
            }
        }
        println("total de fichas: ${fichas.size}")

    }
    fun comprobarCasillas(ficha: FichaAjedrez, posicion: String): List<String>{
        val posiciones = ficha.getPosiblePosition(posicion)
        val posicionesPosibles = mutableListOf<String>()
        if (ficha.tipo == "caballo"){
            for (posicion in posiciones){
                if(isEmpy(posicion)){
                    posicionesPosibles.add(posicion)
                }
        }
            return posicionesPosibles
        }
        var posicionesValidas = mutableListOf<String>()
        for (posicion in posiciones){
            if (!fichas.containsKey(posicion)){
                posicionesValidas.add(posicion)
                    }
                }


        return comprobarVectores(posicionesValidas, posicion)
    }
    fun comprobarVectores(posiciones: List<String>, posicion: String):List<String>{
        var posicionesValidas = mutableListOf<String>()
        var columna = mutableListOf<String>()
        var fila = mutableListOf<String>()
        var diagonalIzq = mutableListOf<String>()
        var diagonalDer = mutableListOf<String>()

        //creamos la columma
        for (p in posiciones){
            if (p.split("-")[0] == posicion.split("-")[0]){
                columna.add(p)
            }
        }
        //verificamos la continuacion de la columna
        columna.sort()
        var columnaA = mutableListOf<String>()
        var columnaB = mutableListOf<String>()
        for (p in columna){
            if (p.split("-")[1].toInt() < posicion.split("-")[1].toInt()){
                columnaA.add(p)
            }else{
                columnaB.add(p)
            }
        }
        columnaA.sort()
        var index = posicion.split("-")[1].toInt()
        for (p in columnaA){
            if (p.split("-")[1].toInt() == index - 1){
                posicionesValidas.add(p)
                index = p.split("-")[1].toInt()
            }
        }
        columnaB.sort()
        index = posicion.split("-")[1].toInt()
        for (p in columnaB){
            if (p.split("-")[1].toInt() == index + 1){
                posicionesValidas.add(p)
                index = p.split("-")[1].toInt()
            }
        }


        //creamos la fila
        for (p in posiciones){
            if (p.split("-")[1] == posicion.split("-")[1]){
                fila.add(p)
            }
        }
        //creamos la diagonal izquierda
        //creamos la diagonal derecha
        //TODO falta crear las diagonales
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
            pintarFicha(ficha,ficha.x,ficha.y,ficha.width)
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
        if (fichas.get(posicion)==null){
            return true
        }
        return false
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
    fun pintarInterfaz(){
        var inferior = Sprite(texturaLadrillo,0,0,Gdx.graphics.width, (Gdx.graphics.height-Gdx.graphics.width)/2)
        //pintar
        batch.begin()
        //superior.draw(batch)
        inferior.draw(batch)
        batch.draw(texturaLadrillo,0f,((Gdx.graphics.height-Gdx.graphics.width)+(Gdx.graphics.width/2)).toFloat(),Gdx.graphics.width.toFloat(), ((Gdx.graphics.height-Gdx.graphics.width)/2).toFloat())
        batch.end()
    }
}