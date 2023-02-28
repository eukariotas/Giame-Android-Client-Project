package es.eukariotas.giame.game.tresEnRaya3D.Objects


import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.PerspectiveCamera
import com.badlogic.gdx.graphics.g3d.ModelBatch


class TicTacToe3D : ApplicationAdapter() {

    private lateinit var camera: PerspectiveCamera
    private lateinit var modelBatch: ModelBatch


    override fun create() {
        // Crear la cámara
        camera = PerspectiveCamera(67f, Gdx.graphics.width.toFloat(), Gdx.graphics.height.toFloat())
        camera.position.set(5f, 5f, 5f)
        camera.lookAt(0f, 0f, 0f)
        camera.near = 0.1f
        camera.far = 100f

        // Crear el ModelBatch
        modelBatch = ModelBatch()


    }

    override fun render() {
        // Limpiar la pantalla
        Gdx.gl.glClearColor(0.3f, 0.3f, 0.3f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT or GL20.GL_DEPTH_BUFFER_BIT)



        // Renderizar el tablero y las celdas
        modelBatch.begin(camera)

        modelBatch.end()
    }
        override fun resize(width: Int, height: Int) {
            camera.viewportWidth = width.toFloat()
            camera.viewportHeight = height.toFloat()
            camera.update()
        }

        override fun dispose() {
            modelBatch.dispose()

        }
    }