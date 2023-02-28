package es.eukariotas.giame.game.Aviones


import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType
import com.badlogic.gdx.math.Rectangle
import net.java.games.input.Component


class AvionesController : ApplicationAdapter() {

    var batch: SpriteBatch? = null
    var main: Texture? = null
    var instMenu: Texture? = null
    var winImg1: Texture? = null
    var winImg2: Texture? = null
    var startBut: Texture? = null
    var startButHigh: Texture? = null
    var instBut: Texture? = null
    var instButHigh: Texture? = null
    var backBut: Texture? = null
    var backButHigh: Texture? = null
    var gameImg: Texture? = null
    var menu: Sprite? = null
    var instruct: Sprite? = null
    var p1Win: Sprite? = null
    var p2Win: Sprite? = null
    var start: Sprite? = null
    var startHigh: Sprite? = null
    var inst: Sprite? = null
    var instHigh: Sprite? = null
    var back: Sprite? = null
    var backHigh: Sprite? = null
    var gameScreen: Sprite? = null
    var page = "main menu"
    var gameOn = false
    var winner: String? = null
    var p1x = 100
    var p1y = 250
    var p2x = 2000
    var p2y = 550
    var mx = 0
    var my = 0
    var speed1 = 2
    var speed2 = 2
    var dir1 = ""
    var dir2 = ""
    var trail1: ArrayList<Rectangle> = ArrayList<Rectangle>()
    var trail2: ArrayList<Rectangle> = ArrayList<Rectangle>()
    var shapeRenderer: ShapeRenderer? = null
    var camera = OrthographicCamera()

    fun AvionesController() {}

    override fun create() {
        batch = SpriteBatch()
        camera.setToOrtho(false, 500F, 500F)
        main = Texture("Menu.jpg")
        menu = Sprite(main)
        instMenu = Texture("Instruct.png")
        instruct = Sprite(instMenu)
        gameImg = Texture("espacio.png")
        gameScreen = Sprite(gameImg)
        winImg1 = Texture("p1 win.jpg")
        winImg2 = Texture("p2 win.jpg")
        p1Win = Sprite(winImg1)
        p2Win = Sprite(winImg2)
        startBut = Texture("Start.png")
        start = Sprite(startBut)
        startButHigh = Texture("Start high.png")
        startHigh = Sprite(startButHigh)
        instBut = Texture("Instruction.png")
        inst = Sprite(instBut)
        instButHigh = Texture("Instruction high.png")
        instHigh = Sprite(instButHigh)
        backBut = Texture("Back.png")
        back = Sprite(backBut)
        backButHigh = Texture("Back high.png")
        backHigh = Sprite(backButHigh)
        shapeRenderer = ShapeRenderer()
        dir1 = "RIGHT"
        dir2 = "A"
        gameOn = true


    }

    fun update() {
        val p1current = Rectangle(p1x.toFloat(), p1y.toFloat(), 10.0f, 10.0f)
        val p2current = Rectangle(p2x.toFloat(), p2y.toFloat(), 10.0f, 10.0f)
        if (trail1.contains(p1current)) {
            winner = "PLAYER 2"
            gameOn = false
        } else if (trail1.contains(p2current)) {
            winner = "PLAYER 1"
            gameOn = false
        } else {
            trail1.add(p1current)
        }
        if (trail2.contains(p1current)) {
            winner = "PLAYER 2"
            gameOn = false
        } else if (trail2.contains(p2current)) {
            winner = "PLAYER 1"
            gameOn = false
        } else {
            trail2.add(p2current)
        }
        if (dir1 == "RIGHT") {
            if (Gdx.input.isKeyPressed(Input.Keys.SHIFT_RIGHT)) {
                speed1 = 3
                p1x += speed1
            } else {
                speed1 = 2
                p1x += speed1
            }
        } else if (dir1 == "LEFT") {
            if (Gdx.input.isKeyPressed(Input.Keys.SHIFT_RIGHT)) {
                speed1 = 3
                p1x -= speed1
            } else {
                speed1 = 2
                p1x -= speed1
            }
        } else if (dir1 == "UP") {
            if (Gdx.input.isKeyPressed(Input.Keys.SHIFT_RIGHT)) {
                speed1 = 3
                p1y += speed1
            } else {
                speed1 = 2
                p1y += speed1
            }
        } else if (dir1 == "DOWN") {
            if (Gdx.input.isKeyPressed(Input.Keys.SHIFT_RIGHT)) {
                speed1 = 3
                p1y -= speed1
            } else {
                speed1 = 2
                p1y -= speed1
            }
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.RIGHT) && dir1 != "LEFT") {
            println("yes")
            dir1 = "RIGHT"
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.LEFT) && dir1 != "RIGHT") {
            dir1 = "LEFT"
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.UP) && dir1 != "DOWN") {
            dir1 = "UP"
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN) && dir1 != "UP") {
            dir1 = "DOWN"
        }
        if (dir2 == "D") {
            if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
                speed2 = 3
                p2x += speed2
            } else {
                speed2 = 2
                p2x += speed2
            }
        } else if (dir2 == "A") {
            if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
                speed2 = 3
                p2x -= speed2
            } else {
                speed2 = 2
                p2x -= speed2
            }
        }
        if (dir2 == "W") {
            if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
                speed2 = 3
                p2y += speed2
            } else {
                speed2 = 2
                p2y += speed2
            }
        } else if (dir2 == "S") {
            if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
                speed2 = 3
                p2y -= speed2
            } else {
                speed2 = 2
                p2y -= speed2
            }
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.D) && dir2 != "A") {
            dir2 = "D"
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.A) && dir2 != "D") {
            dir2 = "A"
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.W) && dir2 != "S") {
            dir2 = "W"
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.S) && dir2 != "W") {
            dir2 = "S"
        }

    }

    fun gameDraw() {
        if (gameOn) {
            update()
            Gdx.gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f)
            Gdx.gl.glClear(16384)
            var i: Int
            i = 0
            while (i < trail1.size) {
                shapeRenderer!!.begin(ShapeType.Filled)
                shapeRenderer!!.color = Color.BLUE
                shapeRenderer!!.rect(trail1[i]!!.getX(), trail1[i]!!.getY(), 5.0f, 5.0f)
                shapeRenderer!!.color = Color.ORANGE
                shapeRenderer!!.end()
                ++i
            }
            i = 0
            while (i < trail2.size) {
                shapeRenderer!!.begin(ShapeType.Filled)
                shapeRenderer!!.color = Color.ORANGE
                shapeRenderer!!.rect(trail2[i]!!.getX(), trail2[i]!!.getY(), 5.0f, 5.0f)
                shapeRenderer!!.end()
                ++i
            }
            if (p1x >= 0 && p1y >= 0 && p1x <= 2215 && p1y <= 1009) {
                if (p2x < 0 || p2y < 0 || p2x > 2215 || p2y > 1009) {
                    page = "p1 winner"
                }
            } else {
                page = "p2 winner"
            }
        } else if (winner == "PLAYER 1") {
            page = "p1 winner"
        } else if (winner == "PLAYER 2") {
            page = "p2 winner"
        }
    }

    fun p1Winner() {
        batch!!.begin()
        p1Win!!.setSize(500.0f, 500.0f)
        p1Win!!.setPosition(0.0f, 0.0f)
        p1Win!!.draw(batch)
        back!!.setSize(130.0f, 50.0f)
        back!!.setPosition(185.0f, 15.0f)
        back!!.draw(batch)
        batch!!.end()
        mx = Gdx.input.x
        my = Gdx.input.y
        if (mx >= 822 && mx <= 1392 && my >= 882 && my <= 981) {
            batch!!.begin()
            backHigh!!.setSize(130.0f, 50.0f)
            backHigh!!.setPosition(185.0f, 15.0f)
            backHigh!!.draw(batch)
            batch!!.end()
            if (Gdx.input.isButtonPressed(0)) {
                page = "main menu"
            }
        }
    }

    fun p2Winner() {
        batch!!.begin()
        p2Win!!.setSize(500.0f, 500.0f)
        p2Win!!.setPosition(0.0f, 0.0f)
        p2Win!!.draw(batch)
        back!!.setSize(130.0f, 50.0f)
        back!!.setPosition(185.0f, 15.0f)
        back!!.draw(batch)
        batch!!.end()
        mx = Gdx.input.x
        my = Gdx.input.y
        if (mx >= 822 && mx <= 1392 && my >= 882 && my <= 981) {
            batch!!.begin()
            backHigh!!.setSize(130.0f, 50.0f)
            backHigh!!.setPosition(185.0f, 15.0f)
            backHigh!!.draw(batch)
            batch!!.end()
            if (Gdx.input.isButtonPressed(0)) {
                page = "main menu"
            }
        }
    }

    fun instructions() {
        batch!!.begin()
        instruct!!.setSize(500.0f, 500.0f)
        instruct!!.setPosition(0.0f, 0.0f)
        instruct!!.draw(batch)
        back!!.setSize(130.0f, 50.0f)
        back!!.setPosition(185.0f, 15.0f)
        back!!.draw(batch)
        batch!!.end()
        mx = Gdx.input.x
        my = Gdx.input.y
        if (mx >= 826 && mx <= 1392 && my >= 882 && my <= 981) {
            batch!!.begin()
            backHigh!!.setSize(130.0f, 50.0f)
            backHigh!!.setPosition(185.0f, 15.0f)
            backHigh!!.draw(batch)
            batch!!.end()
            if (Gdx.input.isButtonPressed(0)) {
                page = "main menu"
            }
        }
    }

    fun reset() {
        p1x = 100
        p1y = 250
        p2x = 2000
        p2y = 550
        dir1 = "RIGHT"
        dir2 = "A"
        winner = ""
        gameOn = true
        trail1.clear()
        trail2.clear()
    }

    fun mainMenu() {
        mx = Gdx.input.x
        my = Gdx.input.y
        batch!!.begin()
        menu!!.setSize(500.0f, 500.0f)
        menu!!.setPosition(0.0f, 0.0f)
        menu!!.draw(batch)
        start!!.setSize(130.0f, 50.0f)
        start!!.setPosition(20.0f, 15.0f)
        start!!.draw(batch)
        inst!!.setSize(130.0f, 50.0f)
        inst!!.setPosition(350.0f, 15.0f)
        inst!!.draw(batch)
        batch!!.end()
        //println("tocado en ${mx} ${my}")
        if (mx >= 94 && mx <= 653 && my >= 882 && my <= 981) {
            batch!!.begin()
            startHigh!!.setSize(130.0f, 50.0f)
            startHigh!!.setPosition(20.0f, 15.0f)
            startHigh!!.draw(batch)
            batch!!.end()
            if (Gdx.input.isButtonPressed(0)) {
                page = "game"
            }
        } else if (mx >= 1558 && mx <= 21220 && my >= 886 && my <= 978) {
            batch!!.begin()
            instHigh!!.setSize(130.0f, 50.0f)
            instHigh!!.setPosition(350.0f, 15.0f)
            instHigh!!.draw(batch)
            batch!!.end()
            if (Gdx.input.isButtonPressed(0)) {
                page = "instructions"
            }
        }
    }

    override fun render() {
        camera.update()
        batch?.projectionMatrix = camera.combined
        if (page == "main menu") {
            mainMenu()
        }
        if (page == "instructions") {
            instructions()
        }
        if (page == "p1 winner") {
            p1Winner()
            reset()
        }
        if (page == "p2 winner") {
            p2Winner()
            reset()
        }
        if (page == "game") {
            gameDraw()
        }

    }
}


