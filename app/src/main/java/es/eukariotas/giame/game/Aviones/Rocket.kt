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

class Rocket {
    private var rocketShape: PolygonShape? = null
    private var bodyDef: BodyDef? = null


    private var imageRocket: Texture? = null
    private var rocketBox: Rectangle? = null
    private var posRocket: Vector2? = null


    constructor(){
        imageRocket = Texture(Gdx.files.internal("avion.png"))


        var bodyDef = BodyDef()
        bodyDef.type = BodyDef.BodyType.DynamicBody
        bodyDef.position.x = 5f
        bodyDef.position.y = 10f


        rocketShape = PolygonShape(imageRocket!!.width / 2, imageRocket!!.height / 2)
        val fixtureDef = FixtureDef()
        fixtureDef.shape = rocketShape
        fixtureDef.density = 1f


        rocketBox = Rectangle()
        rocketBox!!.width = 64f
        rocketBox!!.height = 64f
        posRocket = Vector2(800/2f,480/2f)

        rocketShape?.dispose()
    }

    fun getRocketShpae(): PolygonShape?{
        return rocketShape
    }

    fun getBodyDef(): BodyDef? {
        return bodyDef
    }

    fun getRocket(): Texture? {
        return imageRocket
    }

    fun getPosRocket(): Vector2? {
        return posRocket
    }

}