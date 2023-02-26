package es.eukariotas.giame.game.Aviones

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.math.MathUtils
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.Body
import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType
import com.badlogic.gdx.physics.box2d.CircleShape
import com.badlogic.gdx.physics.box2d.FixtureDef
import com.badlogic.gdx.physics.box2d.PolygonShape

class Meteor(): CircleShape() {
    private var meteorImage = TextureRegion(Texture(Gdx.files.internal("asteroides2.png")))
    private var yRand = MathUtils.random(0 + 64, 480 - 64)
    private var xRand = MathUtils.random(0 + 64, 800 - 64)
    private var posMeteor = Vector2(xRand.toFloat(), yRand.toFloat())
    private var meteorBodyDef = BodyDef().apply {
        type = BodyType.StaticBody
        position.set(posMeteor)
    }
    private var meteorShape = CircleShape().apply {
        radius = .32f
    }
    private var meteorFixture = FixtureDef().apply {
        shape = meteorShape
        density = 15f
        friction = .5f
        restitution = .5f
    }



    init {
        meteorShape.dispose()
    }

    fun getBodyDef(): BodyDef{
        return meteorBodyDef
    }

    fun getFixture(): FixtureDef{
        return meteorFixture
    }

    fun getMeteor(): TextureRegion {
        return meteorImage
    }

    fun getPosMeteor(): Vector2 {
        return posMeteor
    }

    fun reposition() {
        posMeteor = Vector2(xRand.toFloat(), yRand.toFloat())
    }


}