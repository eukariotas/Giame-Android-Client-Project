package es.eukariotas.giame.game.Aviones

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.Body
import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.Fixture
import com.badlogic.gdx.physics.box2d.FixtureDef
import com.badlogic.gdx.physics.box2d.PolygonShape

class Rocket//rocketBox = Rectangle()
//rocketBox!!.width = 64f
//rocketBox!!.height = 64f
//posRocket = Vector2(800/2f,480/2f)
    () : PolygonShape() {

    private var bodyDef = BodyDef().apply {
        type = BodyDef.BodyType.DynamicBody
        position.set(50f, 5f)
    }

    private var imageRocket = Texture(Gdx.files.internal("avion.png"))

    //private var rocketBox: Rectangle? = null
    //private var posRocket: Vector2? = null

    private var rocketShape = PolygonShape().apply {
        setAsBox(2f, 2f)
    }

    private var rFixtureDef = FixtureDef()


    init {
        rFixtureDef.shape = rocketShape
        rFixtureDef.density = 1f
    }

    fun getRocketShape(): FixtureDef{
        return  rFixtureDef
    }

    fun getBodyDef(): BodyDef {
        return bodyDef
    }

    fun getRocket(): Texture {
        return imageRocket
    }

    /*fun getPosRocket(): Vector2? {
        return posRocket
    }*/

}