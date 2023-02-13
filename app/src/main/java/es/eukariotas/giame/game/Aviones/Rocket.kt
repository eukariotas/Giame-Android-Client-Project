package es.eukariotas.giame.game.Aviones

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.Body
import com.badlogic.gdx.physics.box2d.BodyDef

class Rocket {
    private var rBody: Body? = null
    private var bodyDef: BodyDef? = null

    private var imageRocket: Texture? = null
    private var rocketBox: Rectangle? = null
    private var posRocket: Vector2? = null


    constructor(){
        var bodyDef = BodyDef()
        bodyDef.type = BodyDef.BodyType.DynamicBody
        bodyDef.position.x = 0f
        bodyDef.position.y = 0f
        


        //imageRocket = Texture(Gdx.files.internal("avion.png"))
        //rocketBox = Rectangle()
        //rocketBox!!.width = 64f
        //rocketBox!!.height = 64f
        //posRocket = Vector2(800/2f,480/2f)
    }

    fun getRocket(): Texture? {
        return imageRocket
    }

    fun getPosRocket(): Vector2? {
        return posRocket
    }

}