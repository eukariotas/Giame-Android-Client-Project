package es.eukariotas.giame.game.Aviones

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.maps.tiled.TideMapLoader
import com.badlogic.gdx.maps.tiled.TiledMap
import com.badlogic.gdx.maps.tiled.TmxMapLoader
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer
import com.badlogic.gdx.math.MathUtils
import com.badlogic.gdx.math.Matrix4
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.*
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.utils.Array
import com.badlogic.gdx.utils.ScreenUtils


class AvionesController : ApplicationAdapter() {

    private lateinit var world: World

    private lateinit var stg: Stage

    private lateinit var map: TiledMap

    private lateinit var mr: OrthogonalTiledMapRenderer

    private lateinit var sr: ShapeRenderer

    private lateinit var camera: OrthographicCamera

    private lateinit var matrix: Matrix4

    private lateinit var b2dr: Box2DDebugRenderer
    private lateinit var rBody: Body
    private lateinit var batch: SpriteBatch


    override fun create() {
        world = World(Vector2(0f, 0f), false)

        stg = Stage()
        map = TmxMapLoader().load("espacio.tmx")
        mr = OrthogonalTiledMapRenderer(map, 1 / 16f)
        sr = ShapeRenderer()
        camera = OrthographicCamera(20f, 20 * (Gdx.graphics.width / Gdx.graphics.height).toFloat())
        matrix = camera.combined
        b2dr = Box2DDebugRenderer()

        rBody = world.createBody(Rocket().getBodyDef())
        rBody.createFixture(Rocket().getRocketShape())


        //Spawn meteors off screen
        addMeteors()
    }


    override fun render() {

        // tell the camera to update its matrices.

        world.step(Gdx.graphics.deltaTime, 3, 3)
        camera.position.x = rBody.position.x
        camera.position.y = rBody.position.y
        camera.update()
        mr.setView(camera)
        mr.render()
        b2dr.render(world, camera.combined)


        // tell the SpriteBatch to render in the
        // coordinate system specified by the camera.
        //batch!!.projectionMatrix = camera.combined

        // begin a new batch and draw the rocket and meteors
        //batch.begin()
        //batch!!.draw(Rocket().getRocket(), Rocket().getPosRocket()!!.x, Rocket().getPosRocket()!!.y )
        //batch!!.draw(spaceImage, 0f, 0f)

        /*for(Meteor in meteors){
            batch.draw(Meteor.getMeteor(), Meteor.getPosMeteor()!!.x, Meteor.getPosMeteor()!!.y)
        }*/

        //batch.end()

        //if(rocket!!.overlaps(rocket) || rocket!!.overlaps(asteroid)){


        // make sure the bucket stays within the screen bounds
        /* if (rocket!!.x < 0) rocket!!.x = 0f
         if (rocket!!.x > 800 - 64) rocket!!.x = (800 - 64).toFloat()
         if (rocket!!.y < 0) rocket!!.y = 0f
         if (rocket!!.y > 480 - 64) rocket!!.y = (480 - 64).toFloat()*/

    }

    override fun dispose() {
        // dispose of all the native resources
        world.dispose()
        this.stg.dispose()
        this.map.dispose()
        this.mr.dispose()
        this.sr.dispose()
        this.b2dr.dispose()
        this.world.dispose()

    }

    companion object {

        private var meteors = Array<Meteor>()
        fun addMeteors() {
            for (i in 1..MathUtils.random(3, 6)) {
                meteors.add(Meteor())
            }
        }

        /*fun createRocket(){
            var bodyDef = BodyDef.BodyType.DynamicBody

        }*/

    }

}