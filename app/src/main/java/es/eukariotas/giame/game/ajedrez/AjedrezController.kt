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
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.actions.Actions.show
import com.badlogic.gdx.scenes.scene2d.ui.*
import es.eukariotas.giame.ConexionFragment
import es.eukariotas.giame.core.RetrofitHelper

import es.eukariotas.giame.game.ajedrez.Object.CasilaAjedrez
import es.eukariotas.giame.game.ajedrez.Object.FichaAjedrez
import es.eukariotas.giame.persistence.DataBaseProv
import es.eukariotas.giame.persistence.data.apiclient.PartyApiClient
import es.eukariotas.giame.persistence.data.apiclient.TurnApiClient
import es.eukariotas.giame.persistence.data.model.TurnModel
import es.eukariotas.giame.persistence.database.entities.TurnEntity
import kotlinx.coroutines.*

class AjedrezController: ApplicationAdapter() {
    private lateinit var stage: Stage

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
    lateinit var celdaRojaBlanca:Texture
    lateinit var celdaRojaNegra:Texture

    private var camera: OrthographicCamera? = null
    lateinit var batch:SpriteBatch



    /**
     * Al iniciar se cargan las texturas
     * y se realizan las configuraciones iniciales
     */
    @Override
    override fun create() {
        stage = Stage()
        if (ConexionFragment.tipoJuego.equals("online")){
            modo = "online"
            if (DataBaseProv.playerNum == 1){
                color = "blanco"
            }else{
                color = "negro"
            }

            estado = "open"
            esperarContrario()
        }



        tamañoCelda = Gdx.graphics.width.toFloat() / 8//se calcula el tamaño de las celdas
        //texturas
        texturaLadrillo = Texture(Gdx.files.internal("infterface.png"))
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
        celdaRojaBlanca = Texture(Gdx.files.internal("celdaRojaBlanca.png"))
        celdaRojaNegra = Texture(Gdx.files.internal("celdaRojaNegra.png"))
        //se crea la camara
        camera = OrthographicCamera()
        camera!!.setToOrtho(false, Gdx.graphics.width.toFloat(), Gdx.graphics.height.toFloat())
        batch = SpriteBatch()
        //se crea el tablero con las fichas
        getTableroNuevo(tamañoCelda)
        //calculo la posicion donde comenara a pintarse el tablero
        posicionInicialY = (Gdx.graphics.height.toFloat()-Gdx.graphics.width.toFloat())
        //el input processor se encarga de los eventos de entrada
        Gdx.input.inputProcessor = object : InputAdapter(){
            var posiblesPosiciones:List<String> = ArrayList()//lista de posiciones posibles

            var fichaSeleccionada:FichaAjedrez? = null//ficha seleccionada
            var posicionFichaSeleccionada:String? = null//posicion de la ficha seleccionada

            var fichaPulsada:FichaAjedrez? = null//ficha pulsada
            var celdaPulsada:CasilaAjedrez? = null//celda seleccionada
            var posicionCeldaPulsada:String? = null//posicion de la celda seleccionada

        override fun touchDown(screenX: Int, screenY: Int, pointer: Int, button: Int): Boolean {
            //recogemos la posicion de la casilla que se ha tocado
            if (estado.equals("open")){

            }else{
            val x = Gdx.input.x
            val y = (Gdx.input.y-Gdx.graphics.height)*-1
            println("tocado en ${x} ${y}")//se indica donde se ha tocado

            //se obtiene la celda pulsada
            for (celda in tablero){
                if (celda.value.boundingRectangle.contains(x.toFloat(),y.toFloat())){
                    celdaPulsada = celda.value
                    posicionCeldaPulsada = celda.key
                    println("celda pulsada ${celda.key}")
                }
            }
            //si se ha pulsado dentro del tablero se abra pulsado una celda
            if(celdaPulsada!=null){
                //se detecta si se ha pulsado sobre una ficha
                fichaPulsada = fichas[posicionCeldaPulsada]

                //se detecta si anteriormente una ficha estaba seleccionada
                if (fichaSeleccionada !=null){
                    //si se ha pulsado sobre una ficha
                    if (fichaPulsada!=null){
                        if (fichaPulsada!!.color== turnoColor()&& turnoColor().equals(color)){
                        //se ponen las celdas de su color original
                        if (posiblesPosiciones != null){
                            for (posicion in posiblesPosiciones){
                                changeCeldaColor("normal",posicion)
                            }
                        }
                        fichaSeleccionada!!.setSize(tamañoCelda,tamañoCelda)
                        fichas.remove(posicionFichaSeleccionada)
                        fichas.put(posicionFichaSeleccionada!!,fichaSeleccionada!!)
                        //si la ficha pulsada es la misma que la seleccionada se deselecciona
                        if (fichaPulsada == fichaSeleccionada&&posicionCeldaPulsada == posicionFichaSeleccionada){
                            fichaSeleccionada = null//se deselecciona la ficha
                            posiblesPosiciones = ArrayList()//se vacia la lista de posiciones posibles
                        }else{
                            fichaSeleccionada = fichaPulsada//se selecciona la ficha pulsada
                            posicionFichaSeleccionada = posicionCeldaPulsada
                            posiblesPosiciones = comprobarCasillas(fichaSeleccionada!!,posicionFichaSeleccionada!!)
                            for (posicion in posiblesPosiciones){
                                if (isEnemy(posicion,fichaSeleccionada!!.color)){
                                    changeCeldaColor("rojo",posicion)
                                }else{
                                changeCeldaColor("verde",posicion)
                                }
                            }
                            fichaSeleccionada!!.setSize(tamañoCelda-10,tamañoCelda-10)
                            fichas.remove(posicionFichaSeleccionada)
                            fichas.put(posicionFichaSeleccionada!!,fichaSeleccionada!!)
                        }
                        }else{
                            //si se ha pulsado sobre una ficha enemiga se comprueba si se puede comer
                            if (posiblesPosiciones.contains(posicionCeldaPulsada)){
                                var fichaKill = fichas.get(posicionCeldaPulsada)
                                fichas.remove(posicionCeldaPulsada)//se elimina la ficha enemiga

                                fichaSeleccionada!!.setSize(tamañoCelda,tamañoCelda)
                                fichaSeleccionada!!.setPosition(celdaPulsada!!.x,celdaPulsada!!.y)
                                fichas.remove(posicionFichaSeleccionada)
                                fichas.put(posicionCeldaPulsada!!,fichaSeleccionada!!)

                                for (posicion in posiblesPosiciones){
                                    changeCeldaColor("normal",posicion)
                                }
                                fichaSeleccionada = null
                                posiblesPosiciones = ArrayList()
                                if (fichaKill!!.tipo == "rey"){
                                    println("fin del juego")
                                    var tabla = mostrarMensajeFin()
                                    tabla.setPosition(Gdx.graphics.width.toFloat()/2,Gdx.graphics.height.toFloat()/2)
                                    batch.begin()
                                    tabla.draw(batch,1f)
                                    batch.end()
                                }
                                incrementarTurno()
                            }
                        }
                    }else{
                        //si se ha pulsado sobre una celda vacia
                        //se comprueba si la celda pulsada esta en la lista de posiciones posibles
                        if (posiblesPosiciones.contains(posicionCeldaPulsada)){
                            //se mueve la ficha a la celda pulsada
                            fichaSeleccionada!!.setSize(tamañoCelda,tamañoCelda)
                            fichaSeleccionada!!.setPosition(celdaPulsada!!.x,celdaPulsada!!.y)
                            fichas.remove(posicionFichaSeleccionada)
                            fichas.put(posicionCeldaPulsada!!,fichaSeleccionada!!)
                            //se ponen las celdas de su color original
                            for (posicion in posiblesPosiciones){
                                changeCeldaColor("normal",posicion)
                            }
                            fichaSeleccionada = null//se deselecciona la ficha
                            posiblesPosiciones = ArrayList()//se vacia la lista de posiciones posibles
                            incrementarTurno()
                        }

                    }
                }else{
                    //si no hay ninguna ficha seleccionada
                    //se comprueba si se ha pulsado sobre una ficha
                    if (fichaPulsada!=null&&fichaPulsada!!.color == turnoColor()){
                        fichaSeleccionada = fichaPulsada//se selecciona la ficha pulsada
                        posicionFichaSeleccionada = posicionCeldaPulsada//se guarda la posicion de la ficha seleccionada
                        posiblesPosiciones = comprobarCasillas(fichaSeleccionada!!,posicionFichaSeleccionada!!)
                        for (posicion in posiblesPosiciones){
                            if (isEnemy(posicion,fichaSeleccionada!!.color)){
                                changeCeldaColor("rojo",posicion)
                            }else{
                                changeCeldaColor("verde",posicion)
                            }
                        }
                        fichaSeleccionada!!.setSize(tamañoCelda-10,tamañoCelda-10)
                        fichas.remove(posicionFichaSeleccionada)
                        fichas.put(posicionFichaSeleccionada!!,fichaSeleccionada!!)
                    }

                }
            }else{
                //TODO controlar la interfaz
                println("se ha pulsado fuera del tablero")
            }
            }
          return true
        }

        }
    }



    companion object{
        var estado = "started"
        var color: String = "blanco"
        var modo = "maquina"
        var posicionInicialY = 0f
        var tamañoCelda = 0f
        var turno = 1
        var spriteList = mutableListOf<Sprite>()
        var tablero = mutableMapOf<String, CasilaAjedrez>()
        var fichas = mutableMapOf<String, FichaAjedrez>()
        val posicionPantalla = mutableMapOf<String, Vector2>()//por casa posicion del tablero almacenamos su posicion en la pantalla

        fun incrementarTurno(){
            if(modo.equals("online")){
                var posiciones = tableroToFen()
                var turnoToSave =TurnModel(1, posiciones, turno,false, DataBaseProv.partidaActual!!)

                CoroutineScope(Dispatchers.IO).launch {
                    val call = RetrofitHelper.getRetrofit().create(TurnApiClient::class.java).saveTurno(turnoToSave)
                            val response = call.execute()
                            if(response.isSuccessful){
                                val turno = response.body()
                                println("Turno guardado")
                            }else{
                                println("Error al guardar el turno")
                            }


                }

            }else{
                turno++
            }

        }
        fun turnoColor():String{
            if (turno%2==0){
                return "negro"
            }else{
                return "blanco"
            }
        }

        fun tableroToFen():String{
            val filas = "12345678"
            val columnas = "abcdefgh"
            var fen = ""
            var contador = 0
            for (i in 1..8){
                for (j in 0..7){
                    var posicion = columnas.get(j)+"-"+i
                    println("posicion: "+posicion)
                    if (fichas.get(posicion)!=null){
                        if (contador!=0){
                            fen+=contador.toString()
                            contador = 0
                        }
                        fen+= fichas[posicion]!!.getInicial()
                    }else{
                        contador++
                    }
                    if (j==7){
                        if (contador!=0){
                            fen+=contador.toString()
                            contador = 0
                        }
                        if (i!=8){
                            fen+="/"
                        }
                    }
                }
            }
            return fen
        }
    }

    override fun render() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)


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
        val fila = posicion.split("-")[1].toInt()
        val columna = FichaAjedrez.letras.indexOf(posicion.split("-")[0])+1
        val posiciones = ficha.getPosiblePosition(posicion)//obtenemos todas las posiciones posibles
        val posicionesPosibles = mutableListOf<String>()
        if (ficha.tipo == "caballo"){
            for (posicion in posiciones){
                if(isEnemy(posicion,ficha.color)){
                    posicionesPosibles.add(posicion)
                }else if (isEmpy(posicion)){
                    posicionesPosibles.add(posicion)
                }
        }
            return posicionesPosibles
        }
        if (ficha.tipo == "peon"){
            //comprobamos si hay enemigos
            if (ficha.color=="blanco"){
            var posiccionIzq = "${getColumna(columna-1)}-${fila+1}"
            var posiccionDer = "${getColumna(columna+1)}-${fila+1}"
            if (isEnemy(posiccionIzq,ficha.color)){
                posicionesPosibles.add(posiccionIzq)
            }
            if (isEnemy(posiccionDer,ficha.color)){
                posicionesPosibles.add(posiccionDer)
            }
                for (posicion in posiciones){
                        if(isEmpy(posicion)){
                        posicionesPosibles.add(posicion)
                    }else{
                        break
                    }
                }
            }else{
                posiciones.sortedDescending()
                var posiccionIzq = "${getColumna(columna-1)}-${fila-1}"
                var posiccionDer = "${getColumna(columna+1)}-${fila-1}"
                if (isEnemy(posiccionIzq,ficha.color)){
                    posicionesPosibles.add(posiccionIzq)
                }
                if (isEnemy(posiccionDer,ficha.color)){
                    posicionesPosibles.add(posiccionDer)
                }
                for (posicion in posiciones){
                    if(isEmpy(posicion)){
                        posicionesPosibles.add(posicion)
                    }else{
                        break
                    }
                }
            }
            return posicionesPosibles
        }
        if (ficha.tipo == "torre"){
            return comprobarVectores(posiciones,posicion,ficha)
        }
        if (ficha.tipo == "alfil"){
            return clasificarPosicionesEnDiagonal(posiciones,posicion,ficha)
        }
        if (ficha.tipo == "reina"){
            val posicionesTorre = comprobarVectores(ficha.posibleMoveTorre(posicion),posicion,ficha)
            val posicionesAlfil = clasificarPosicionesEnDiagonal(ficha.posibleMoveAlfil(posicion),posicion,ficha)
            posicionesPosibles.addAll(posicionesTorre)
            posicionesPosibles.addAll(posicionesAlfil)
            return posicionesPosibles
        }
        if (ficha.tipo == "rey"){
            for (posicion in posiciones){
                if(isEnemy(posicion,ficha.color)){
                    posicionesPosibles.add(posicion)
                }else if (isEmpy(posicion)){
                    posicionesPosibles.add(posicion)
                }
            }
            return posicionesPosibles
        }

        return comprobarVectores(posiciones,posicion,ficha)
    }
    fun comprobarVectores(posiciones: List<String>, posicion: String, ficha: FichaAjedrez):List<String>{
        val filas = "12345678"
        val columnas = "abcdefgh"

        val posFila = filas.indexOf(posicion[2])
        val posColumna = columnas.indexOf(posicion[0])

        var posicionesAscendentes = mutableListOf<String>()
        var posicionesDescendentes = mutableListOf<String>()
        var posicionesIzquierda = mutableListOf<String>()
        var posicionesDerecha = mutableListOf<String>()

        var posicionesPosibles = mutableListOf<String>()

        for (posicion in posiciones) {
            val fila = filas.indexOf(posicion[2])
            val columna = columnas.indexOf(posicion[0])

            if (fila == posFila) {
                if (columna > posColumna) {
                    posicionesDerecha.add(posicion)
                } else if (columna < posColumna) {
                    posicionesIzquierda.add(posicion)
                }
            } else if (columna == posColumna) {
                if (fila > posFila) {
                    posicionesAscendentes.add(posicion)
                } else if (fila < posFila) {
                    posicionesDescendentes.add(posicion)
                }
            }

        }
        posicionesAscendentes.sorted()
        posicionesDescendentes.sorted()
        posicionesDescendentes = ordenarPosiciones(posicionesDescendentes) as MutableList<String>
        posicionesIzquierda = ordenarPosicionesColumna(posicionesIzquierda) as MutableList<String>
        posicionesDerecha.sorted()

        for (posicion in posicionesAscendentes) {
            if (isEmpy(posicion)) {
                posicionesPosibles.add(posicion)
            } else if (isEnemy(posicion, ficha.color)) {
                posicionesPosibles.add(posicion)
                break
            } else {
                break
            }
        }
        for (posicion in posicionesDescendentes) {
            if (isEmpy(posicion)) {
                posicionesPosibles.add(posicion)
            } else if (isEnemy(posicion, ficha.color)) {
                posicionesPosibles.add(posicion)
                break
            } else {
                break
            }
        }
        for (posicion in posicionesIzquierda) {
            if (isEmpy(posicion)) {
                posicionesPosibles.add(posicion)
            } else if (isEnemy(posicion, ficha.color)) {
                posicionesPosibles.add(posicion)
                break
            } else {
                break
            }
        }
        for (posicion in posicionesDerecha) {
            if (isEmpy(posicion)) {
                posicionesPosibles.add(posicion)
            } else if (isEnemy(posicion, ficha.color)) {
                posicionesPosibles.add(posicion)
                break
            } else {
                break
            }
        }
        return posicionesPosibles
    }

    fun clasificarPosicionesEnDiagonal(posiciones: List<String>, posicionInicial: String, ficha: FichaAjedrez): List<String> {

        println("posiciones: $posiciones")
        var diagonalAscendenteDerecha = mutableListOf<String>()
        var diagonalAscendenteIzquierda = mutableListOf<String>()
        var diagonalDescendenteDerecha = mutableListOf<String>()
        var diagonalDescendenteIzquierda = mutableListOf<String>()

        var posicionesPosibles = mutableListOf<String>()

        var columnaInicial = FichaAjedrez.letras.indexOf(posicionInicial.split("-")[0])
        val filaInicial = posicionInicial[2].toString().toInt()

        for (posicion in posiciones) {
            val columnaActual = FichaAjedrez.letras.indexOf(posicion.split("-")[0])
            val filaActual = posicion[2].toString().toInt()

            val diferenciaColumnas = columnaActual - columnaInicial
            val diferenciaFilas = filaActual - filaInicial

            if (diferenciaFilas == diferenciaColumnas) {
                if (filaActual > filaInicial) {
                    diagonalAscendenteDerecha.add(posicion)
                }
                else if (filaActual < filaInicial) {
                    diagonalDescendenteIzquierda.add(posicion)
                }
            }
            else if (diferenciaFilas == -diferenciaColumnas) {
                if (filaActual > filaInicial) {
                    diagonalAscendenteIzquierda.add(posicion)
                }
                else if (filaActual < filaInicial) {
                    diagonalDescendenteDerecha.add(posicion)
                }
            }
        }

        diagonalAscendenteDerecha.sorted()
        diagonalAscendenteIzquierda.sorted()
        diagonalDescendenteDerecha.sorted()
        diagonalDescendenteIzquierda.sorted()

        for (posicion in diagonalAscendenteDerecha) {
            if (isEmpy(posicion)) {
                posicionesPosibles.add(posicion)
            } else if (isEnemy(posicion, ficha.color)) {
                posicionesPosibles.add(posicion)
                break
            } else {
                break
            }
        }

        for (posicion in diagonalAscendenteIzquierda) {
            if (isEmpy(posicion)) {
                posicionesPosibles.add(posicion)
            } else if (isEnemy(posicion, ficha.color)) {
                posicionesPosibles.add(posicion)
                break
            } else {
                break
            }
        }

        for (posicion in diagonalDescendenteDerecha) {
            if (isEmpy(posicion)) {
                posicionesPosibles.add(posicion)
            } else if (isEnemy(posicion, ficha.color)) {
                posicionesPosibles.add(posicion)
                break
            } else {
                break
            }
        }

        for (posicion in diagonalDescendenteIzquierda) {
            if (isEmpy(posicion)) {
                posicionesPosibles.add(posicion)
            } else if (isEnemy(posicion, ficha.color)) {
                posicionesPosibles.add(posicion)
                break
            } else {
                break
            }
        }
        return posicionesPosibles
    }


    fun ordenarPosiciones(posiciones: List<String>):List<String>{
        var mapPos = mutableMapOf<Int,String>()
        for (posicion in posiciones){
            var fila = posicion[2].toString().toInt()
            var columna = posicion[0].toString()
            mapPos.put(fila,columna)
        }
        val mapaOrdenado = mapPos.toList()
            .sortedByDescending { it.first }
            .toMap()
            .toMutableMap()
        var posicionesOrdenadas = mutableListOf<String>()
        for (posicion in mapaOrdenado){
            posicionesOrdenadas.add("${posicion.value}-${posicion.key}")
        }
        return posicionesOrdenadas
    }
    fun ordenarPosicionesColumna(posiciones: List<String>):List<String>{
        var mapPos = mutableMapOf<String,Int>()
        for (posicion in posiciones){
            var fila = posicion[2].toString().toInt()
            var columna = posicion[0].toString()
            mapPos.put(columna,fila)
        }
        val mapaOrdenado = mapPos.toList()
            .sortedByDescending { it.first }
            .toMap()
            .toMutableMap()
        var posicionesOrdenadas = mutableListOf<String>()
        for (posicion in mapaOrdenado){
            posicionesOrdenadas.add("${posicion.key}-${posicion.value}")
        }
        return posicionesOrdenadas
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
        if (fichas[posicion]!=null){
            return false
        }
        return true
    }

    /**
     * Detecta si la casilla de destino esta ocupada por una ficha del adversario
     */
    fun isEnemy(posicion:String, myColor:String):Boolean{
       if(isEmpy(posicion)){
           return false
       }else {
           return fichas[posicion]!!.color != myColor
       }
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
            "rojo" -> if (casilla!!.color == "blanco") {
                casilla!!.texture = celdaRojaBlanca
            } else {
                casilla!!.texture = celdaRojaNegra
            }
        }
        tablero.put(posicion, casilla)
    }
    fun pintarInterfaz(){
        //pintar
        batch.begin()
        //superior.draw(batch)

        batch.draw(texturaLadrillo,0f,((Gdx.graphics.height-Gdx.graphics.width)+(Gdx.graphics.width/2)).toFloat(),Gdx.graphics.width.toFloat(), ((Gdx.graphics.height-Gdx.graphics.width)/2).toFloat())
        batch.draw(texturaLadrillo,0f,0f,Gdx.graphics.width.toFloat(), ((Gdx.graphics.height-Gdx.graphics.width)/2).toFloat())
        batch.end()
    }
    fun mostrarMensajeFin(): Table{
        var tabla = Table()
        var skin = Skin()
        skin.add("fondo",texturaLadrillo)

        tabla.skin = skin
        tabla.background = skin.getDrawable("fondo")
        tabla.setSize(100f,100f)
        tabla.setFillParent(true)

        return tabla

    }

    fun getPartida(){

    }

    fun esperarContrario() {
        CoroutineScope(Dispatchers.IO).launch {
            while (estado.equals("open")){
                delay(1000)
                val call = RetrofitHelper.getRetrofit().create(PartyApiClient::class.java).getParty(DataBaseProv.partidaActual!!.id)
                if (call.isSuccessful){
                    val partida = call.body()
                    if(partida!=null){
                        estado = partida.status
                    }
                }
            }
        }
    }
}