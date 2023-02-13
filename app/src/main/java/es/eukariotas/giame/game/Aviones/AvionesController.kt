package es.eukariotas.giame.game.Aviones

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.audio.Music
import com.badlogic.gdx.audio.Sound
import com.badlogic.gdx.graphics.Camera
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.math.MathUtils
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.*
import com.badlogic.gdx.utils.Array


class AvionesController : ApplicationAdapter() {
    private val world = World(Vector2(0f, 0f), false)
    private val camera = OrthographicCamera()
    private val b2dr = Box2DDebugRenderer()
    private val rBody: Body? = null




    override fun create() {
        val w = Gdx.graphics.width.toFloat()
        val h = Gdx.graphics.height.toFloat()
        camera.setToOrtho(false, w / 2, h / 2)
        //val rocket = createRocket()



        //Spawn meteors off screen
        addMeteors()
    }


    override fun render() {
        // tell the camera to update its matrices.
        camera.update()

        // tell the SpriteBatch to render in the
        // coordinate system specified by the camera.
        batch!!.projectionMatrix = camera.combined

        // begin a new batch and draw the bucket and
        batch!!.begin()
        sprite!!.draw(batch)
        //batch!!.draw(spaceImage, 0f, 0f)

        for(Meteor in meteors){
            batch!!.draw(Meteor.getMeteor(), Meteor.getPosMeteor()!!.x, Meteor.getPosMeteor()!!.y)
        }

        batch!!.end()

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
        b2dr.dispose()

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




