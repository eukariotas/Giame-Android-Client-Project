package es.eukariotas.giame.game.Aviones

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.audio.Music
import com.badlogic.gdx.audio.Sound
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.math.MathUtils
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.math.Vector3
import com.badlogic.gdx.utils.Array
import com.badlogic.gdx.utils.TimeUtils

class AvionesController : ApplicationAdapter() {
    private var rocketImage: Texture? = null
    private var asteroidImage: Texture? = null
    private var spaceImage: Texture? = null
    private val dropSound: Sound? = null
    private val rainMusic: Music? = null
    private var batch: SpriteBatch? = null
    private var sprite: Sprite? = null
    private var camera: OrthographicCamera? = null
    private var rocket: Rectangle? = null
    private var rainAsteroids: Array<Rectangle>? = null
    private var lastAsteroidTime: Long = 0
    private val spriteBatch: SpriteBatch? = null
    override fun create() {
        // load the images for the droplet and the bucket, 64x64 pixels each
        rocketImage = Texture(Gdx.files.internal("asteroides2.png"))
        asteroidImage = Texture(Gdx.files.internal("avion.png"))
        spaceImage = Texture(Gdx.files.internal("espacio.png"))

        // load the drop sound effect and the rain background "music"
        // dropSound = Gdx.audio.newSound(Gdx.files.internal("drop.wav"));
        //rainMusic = Gdx.audio.newMusic(Gdx.files.internal("rain.mp3"));

        // start the playback of the background music immediately
        //rainMusic.setLooping(true);
        //rainMusic.play();

        // create the camera and the SpriteBatch
        camera = OrthographicCamera()
        camera!!.setToOrtho(false, 800f, 480f)
        batch = SpriteBatch()
        val region = TextureRegion(spaceImage, 0, 0, 640, 360)
        sprite = Sprite(region)
        sprite!!.setSize(1f, 1f * sprite!!.height / sprite!!.width)

        // create a Rectangle to logically represent the bucket
        rocket = Rectangle()
        rocket!!.x = (800 / 2 - 64 / 2).toFloat() // center the bucket horizontally
        rocket!!.y =
            20f // bottom left corner of the bucket is 20 pixels above the bottom screen edge
        rocket!!.width = 64f
        rocket!!.height = 64f

        // create the raindrops array and spawn the first raindrop
        rainAsteroids = Array()
        spawnRainAsteroids()
    }

    private fun spawnRainAsteroids() {
        val raindrop = Rectangle()
        raindrop.x = MathUtils.random(0, 800 - 64).toFloat()
        raindrop.y = 480f
        raindrop.width = 64f
        raindrop.height = 64f
        rainAsteroids!!.add(raindrop)
        lastAsteroidTime = TimeUtils.nanoTime()
    }

    override fun render() {
        // clear the screen with a dark blue color. The
        // arguments to clear are the red, green
        // blue and alpha component in the range [0,1]
        // of the color to be used to clear the screen.


        // tell the camera to update its matrices.
        camera!!.update()

        // tell the SpriteBatch to render in the
        // coordinate system specified by the camera.
        batch!!.projectionMatrix = camera!!.combined

        // begin a new batch and draw the bucket and
        // all drops
        batch!!.begin()
        sprite!!.draw(batch)
        batch!!.draw(spaceImage, 0f, 0f)
        batch!!.draw(asteroidImage, rocket!!.x, rocket!!.y)
        for (raindrop in rainAsteroids!!) {
            batch!!.draw(rocketImage, raindrop.x, raindrop.y)
        }
        batch!!.end()

        // process user input
        if (Gdx.input.isTouched) {
            val touchPos = Vector3()
            touchPos[Gdx.input.x.toFloat(), Gdx.input.y.toFloat()] = 0f
            camera!!.unproject(touchPos)
            rocket!!.x = touchPos.x - 64 / 2
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) rocket!!.x -= 200 * Gdx.graphics.deltaTime
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) rocket!!.x += 200 * Gdx.graphics.deltaTime

        // make sure the bucket stays within the screen bounds
        if (rocket!!.x < 0) rocket!!.x = 0f
        if (rocket!!.x > 800 - 64) rocket!!.x = (800 - 64).toFloat()

        // check if we need to create a new raindrop
        if (TimeUtils.nanoTime() - lastAsteroidTime > 1000000000) spawnRainAsteroids()

        // move the raindrops, remove any that are beneath the bottom edge of
        // the screen or that hit the bucket. In the latter case we play back
        // a sound effect as well.
        val iter = rainAsteroids!!.iterator()
        while (iter.hasNext()) {
            val raindrop = iter.next()
            raindrop.y -= 200 * Gdx.graphics.deltaTime
            if (raindrop.y + 64 < 0) iter.remove()
            if (raindrop.overlaps(rocket)) {
                //dropSound.play();
                iter.remove()
            }
        }
    }

    override fun dispose() {
        // dispose of all the native resources
        rocketImage!!.dispose()
        asteroidImage!!.dispose()
        spaceImage!!.dispose()
        //dropSound.dispose();
        //rainMusic.dispose();
        batch!!.dispose()
        spriteBatch!!.dispose()
    }

    companion object {
        var backgroundSprite: Sprite? = null
    }
}