package es.eukariotas.giame.game.Aviones

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.maps.tiled.TiledMap
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer
import com.badlogic.gdx.math.MathUtils
import com.badlogic.gdx.math.Matrix4
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.math.Vector3
import com.badlogic.gdx.physics.box2d.*
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.InputListener
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Table
import com.badlogic.gdx.utils.Array
import com.badlogic.gdx.utils.viewport.FitViewport


class AvionesController : ApplicationAdapter() {

    private lateinit var world: World
    private lateinit var gravity: Vector2
    private lateinit var b2dr: Box2DDebugRenderer
    private lateinit var stg: Stage
    private lateinit var view: FitViewport

    private lateinit var map: TiledMap

    private lateinit var mr: OrthogonalTiledMapRenderer

    private lateinit var sr: ShapeRenderer

    private lateinit var camera: OrthographicCamera

    private lateinit var matrix: Matrix4


    private lateinit var rBody: Body
    private lateinit var mBody: Body
    private lateinit var batch: SpriteBatch

    private lateinit var vel: Vector2
    private lateinit var pos: Vector2


    override fun create() {
        gravity = Vector2(0f, 0f)
        world = World(gravity, true)
        batch = SpriteBatch()
        camera = OrthographicCamera()
        view = FitViewport(800F, 480f, camera)
        stg = Stage(view, batch)
        Gdx.input.inputProcessor = stg
        b2dr = Box2DDebugRenderer()






        //map = TmxMapLoader().load("espacio.tmx")
        //mr = OrthogonalTiledMapRenderer(map, 1/32f)
        //sr = ShapeRenderer()
        //sr.projectionMatrix = camera.combined



        //matrix = camera.combined
        addMeteors()

        rBody = world.createBody(Rocket().getBodyDef())
        rBody.createFixture(Rocket().getFixture())

        vel = this.rBody.linearVelocity
        pos = this.rBody.position

        rocketMovement()
        //Spawn meteors


    }



    override fun render() {


        world.step(1/60f, 6, 2)

        camera.position.set(view.worldWidth/2 , view.worldHeight/2, 0f)
        camera.update()


        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
        b2dr.render(world, camera.combined)


        batch.begin()

        batch.draw(Texture(Gdx.files.internal("espacio.png")), 0f, 0f, view.worldWidth, view.worldHeight)

        batch.draw(Rocket().getRocket(), rBody.position.x, rBody.position.y )

        for(mBody in meteors){
            batch.draw(Meteor().getMeteor(), mBody.position.x, mBody.position.y)
        }

        batch.end()


        //mr.setView(camera)
        //mr.render()





        
        if (Gdx.input.isKeyPressed(Input.Keys.A) && vel.x > -1f) {
            this.rBody.applyLinearImpulse(-0.80f, 0F, pos.x, pos.y, true);
        }




        //batch.projectionMatrix = camera.combined




        batch.draw(Rocket().getRocket(), rBody.position.x, rBody.position.y)





        //if(rocket!!.overlaps(rocket) || rocket!!.overlaps(asteroid)){


        // make sure the bucket stays within the screen bounds
        /* if (rocket!!.x < 0) rocket!!.x = 0f
         if (rocket!!.x > 800 - 64) rocket!!.x = (800 - 64).toFloat()
         if (rocket!!.y < 0) rocket!!.y = 0f
         if (rocket!!.y > 480 - 64) rocket!!.y = (480 - 64).toFloat()*/


    }

    override fun dispose() {
        // dispose of all the native resources
        this.stg.dispose()
        this.map.dispose()
        this.mr.dispose()
        this.sr.dispose()
        this.b2dr.dispose()
        this.world.dispose()

    }

    private fun addMeteors() {
        for (i in 1..MathUtils.random(3, 6)) {
            mBody = world.createBody(Meteor().getBodyDef())
            mBody.createFixture(Meteor().getFixture())
            meteors.add(mBody)
        }
    }

    private fun rocketMovement(){
        if(Gdx.input.isKeyPressed(Input.Keys.ANY_KEY)){
            if (Gdx.input.isKeyPressed(Input.Keys.A) && vel.x > -1000f) {
                this.rBody.applyLinearImpulse(-100f, 0F, pos.x, pos.y, true);
            }

            // apply right impulse, but only if max velocity is not reached yet
            if (Gdx.input.isKeyPressed(Input.Keys.D) && vel.x < 1000f) {
                this.rBody.applyLinearImpulse(100f, 0F, pos.x, pos.y, true);
            }
            if (Gdx.input.isKeyPressed(Input.Keys.S) && vel.y > -1000f) {
                this.rBody.applyLinearImpulse(0f, -100F, pos.x, pos.y, true);
            }

            // apply right impulse, but only if max velocity is not reached yet
            if (Gdx.input.isKeyPressed(Input.Keys.W) && vel.y < 1000f) {
                this.rBody.applyLinearImpulse(0f, 100F, pos.x, pos.y, true);
            }
        }else{
            vel.x = 0f
            vel.y = 0f
        }

    }


    companion object {

        private var meteors = Array<Body>()

    }

}

