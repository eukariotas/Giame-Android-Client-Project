package es.eukariotas.giame.game.Aviones

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.math.MathUtils
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.math.Vector2

class Meteor {
    private var meteorImage: Texture? = null
    private var posMeteor: Vector2? = null
    private var yRand: Int? = null
    private var xRand: Int? = null
    private var meteorBox: Rectangle? = null

    constructor(){
        meteorImage = Texture(Gdx.files.internal("asteroides2.png"))
        meteorBox = Rectangle()
        meteorBox!!.height = 64f
        meteorBox!!.width = 64f
        yRand = MathUtils.random(0 + 64, 480 - 64)
        xRand = MathUtils.random(0 + 64, 800 - 64)

        //Spawn location
        posMeteor = Vector2(xRand!!.toFloat(), yRand!!.toFloat())
    }

    fun getMeteor(): Texture? {
        return meteorImage
    }

    fun getPosMeteor(): Vector2? {
        return posMeteor
    }

    fun reposition() {
        posMeteor = Vector2(xRand!!.toFloat(), yRand!!.toFloat())
    }
}