package es.eukariotas.giame.game.Aviones

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.maps.tiled.TiledMap
import com.badlogic.gdx.maps.tiled.TmxMapLoader
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer
import com.badlogic.gdx.math.MathUtils
import com.badlogic.gdx.math.Matrix4
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.*
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.utils.Array


class AvionesController : ApplicationAdapter() {

    private lateinit var world: World
    private lateinit var gravity: Vector2
    private lateinit var b2dr: Box2DDebugRenderer

    private lateinit var rTexture: Texture

    private lateinit var stg: Stage

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

        b2dr = Box2DDebugRenderer()

        batch = SpriteBatch()



        //stg = Stage()
        //map = TmxMapLoader().load("espacio.tmx")
        //mr = OrthogonalTiledMapRenderer(map, 1/32f)
        //sr = ShapeRenderer()
        //sr.projectionMatrix = camera.combined

        camera = OrthographicCamera()
        camera.setToOrtho(false, 800f, 480f)

        //matrix = camera.combined


        rBody = world.createBody(Rocket().getBodyDef())
        rBody.createFixture(Rocket().getFixture())

        vel = this.rBody.linearVelocity
        pos = this.rBody.position
        //Spawn meteors off screen
        addMeteors()
    }


    override fun render() {
        world.step(Gdx.graphics.deltaTime, 3, 3)

        // tell the camera to update its matrices.
        //camera.position.x = rBody.position.x
        //camera.position.y = rBody.position.y

        camera.update()

        //mr.setView(camera)
        //mr.render()
        batch.projectionMatrix = camera.combined;



        if (Gdx.input.isKeyPressed(Input.Keys.A) && vel.x > -1f) {
            this.rBody.applyLinearImpulse(-0.80f, 0F, pos.x, pos.y, true);
        }

        // apply right impulse, but only if max velocity is not reached yet
        if (Gdx.input.isKeyPressed(Input.Keys.D) && vel.x < 1f) {
            this.rBody.applyLinearImpulse(0.80f, 0F, pos.x, pos.y, true);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S) && vel.y > -1f) {
            this.rBody.applyLinearImpulse(0f, -0.2F, pos.x, pos.y, true);
        }

        // apply right impulse, but only if max velocity is not reached yet
        if (Gdx.input.isKeyPressed(Input.Keys.W) && vel.y < 1f) {
            this.rBody.applyLinearImpulse(0f, 0.2F, pos.x, pos.y, true);
        }
        // tell the SpriteBatch to render in the
        // coordinate system specified by the camera.
        //batch.projectionMatrix = camera.combined

        // begin a new batch and draw the rocket and meteors
        batch.begin()
        //batch.draw(rTexture, rBody.position.x, rBody.position.y)
        batch.draw(Texture(Gdx.files.internal("espacio.png")), 0f, 0f)

        batch.draw(Rocket().getRocket(), rBody.position.x-22, rBody.position.y-22)

        for(mBody in meteors){
            batch.draw(Meteor().getMeteor(), mBody.position.x, mBody.position.y)
        }

        batch.end()

        //if(rocket!!.overlaps(rocket) || rocket!!.overlaps(asteroid)){


        // make sure the bucket stays within the screen bounds
        /* if (rocket!!.x < 0) rocket!!.x = 0f
         if (rocket!!.x > 800 - 64) rocket!!.x = (800 - 64).toFloat()
         if (rocket!!.y < 0) rocket!!.y = 0f
         if (rocket!!.y > 480 - 64) rocket!!.y = (480 - 64).toFloat()*/

        b2dr.render(world, camera.combined)
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

    fun addMeteors() {
        for (i in 1..MathUtils.random(3, 6)) {
            mBody = world.createBody(Meteor().getBodyDef())
            mBody.createFixture(Meteor().getFixture())
            meteors.add(mBody)
        }
    }
    companion object {

        private var meteors = Array<Body>()


        /*fun createRocket(){
            var bodyDef = BodyDef.BodyType.DynamicBody

        }*/

    }

}

