package es.eukariotas.giame.game.Aviones

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.math.MathUtils
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.Body
import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.Fixture
import com.badlogic.gdx.physics.box2d.FixtureDef
import com.badlogic.gdx.physics.box2d.PolygonShape

class Rocket() : PolygonShape() {
    private var imageRocket = TextureRegion(Texture(Gdx.files.internal("avion.png")))
    private var yRand = MathUtils.random(0 + 64, 480 - 64)
    private var xRand = MathUtils.random(0 + 64, 800 - 64)
    private var posRocket = Vector2(xRand.toFloat(), yRand.toFloat())

    private var rocketBodyDef = BodyDef().apply {
        type = BodyDef.BodyType.DynamicBody
        position.set(posRocket.x, posRocket.y)
    }
    private var rocketShape = PolygonShape().apply {
        setAsBox(11f, 32f)
    }

    private var rocketFixtureDef = FixtureDef().apply {
        shape = rocketShape
        density = 15f
        friction = .5f
    }


    init {
        rocketShape.dispose()
    }

    fun getBodyDef(): BodyDef {
        return rocketBodyDef
    }

    fun getFixture(): FixtureDef{
        return  rocketFixtureDef
    }

    fun getRocket(): TextureRegion {
        return imageRocket
    }

    /*fun getPosRocket(): Vector2? {
        return posRocket
    }*/

}