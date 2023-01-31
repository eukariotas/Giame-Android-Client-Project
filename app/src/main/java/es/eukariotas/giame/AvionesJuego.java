package es.eukariotas.giame;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.TimeUtils;

import java.util.Iterator;

public class AvionesJuego extends ApplicationAdapter {
    private Texture rocketImage;
    private Texture asteroidImage;

    private Texture spaceImage;
    private Sound dropSound;
    private Music rainMusic;
    private SpriteBatch batch;

    private Sprite sprite;
    private OrthographicCamera camera;
    private Rectangle rocket;
    private Array<Rectangle> rainAsteroids;
    private long lastAsteroidTime;

    public static Sprite backgroundSprite;
    private SpriteBatch spriteBatch;

    @Override
    public void create() {
        // load the images for the droplet and the bucket, 64x64 pixels each
        rocketImage = new Texture(Gdx.files.internal("asteroides2.png"));
        asteroidImage = new Texture(Gdx.files.internal("avion.png"));
        spaceImage = new Texture(Gdx.files.internal("espacio.png"));

        // load the drop sound effect and the rain background "music"
        // dropSound = Gdx.audio.newSound(Gdx.files.internal("drop.wav"));
        //rainMusic = Gdx.audio.newMusic(Gdx.files.internal("rain.mp3"));

        // start the playback of the background music immediately
        //rainMusic.setLooping(true);
        //rainMusic.play();

        // create the camera and the SpriteBatch
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
        batch = new SpriteBatch();

        TextureRegion region = new TextureRegion(spaceImage, 0 , 0, 640, 360);
        sprite = new Sprite(region);
        sprite.setSize(1f, 1f * sprite.getHeight() / sprite.getWidth() );

        // create a Rectangle to logically represent the bucket
        rocket = new Rectangle();
        rocket.x = 800 / 2 - 64 / 2; // center the bucket horizontally
        rocket.y = 20; // bottom left corner of the bucket is 20 pixels above the bottom screen edge
        rocket.width = 64;
        rocket.height = 64;

        // create the raindrops array and spawn the first raindrop
        rainAsteroids = new Array<Rectangle>();
        spawnRainAsteroids();
    }

    private void spawnRainAsteroids() {
        Rectangle raindrop = new Rectangle();
        raindrop.x = MathUtils.random(0, 800-64);
        raindrop.y = 480;
        raindrop.width = 64;
        raindrop.height = 64;
        rainAsteroids.add(raindrop);
        lastAsteroidTime = TimeUtils.nanoTime();
    }


    @Override
    public void render() {
        // clear the screen with a dark blue color. The
        // arguments to clear are the red, green
        // blue and alpha component in the range [0,1]
        // of the color to be used to clear the screen.


        // tell the camera to update its matrices.
        camera.update();

        // tell the SpriteBatch to render in the
        // coordinate system specified by the camera.
        batch.setProjectionMatrix(camera.combined);

        // begin a new batch and draw the bucket and
        // all drops
        batch.begin();
        sprite.draw(batch);
        batch.draw(spaceImage,0,0);
        batch.draw(asteroidImage, rocket.x, rocket.y);
        for(Rectangle raindrop: rainAsteroids) {
            batch.draw(rocketImage, raindrop.x, raindrop.y);
        }
        batch.end();

        // process user input
        if(Gdx.input.isTouched()) {
            Vector3 touchPos = new Vector3();
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touchPos);
            rocket.x = touchPos.x - 64 / 2;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) rocket.x -= 200 * Gdx.graphics.getDeltaTime();
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) rocket.x += 200 * Gdx.graphics.getDeltaTime();

        // make sure the bucket stays within the screen bounds
        if(rocket.x < 0) rocket.x = 0;
        if(rocket.x > 800 - 64) rocket.x = 800 - 64;

        // check if we need to create a new raindrop
        if(TimeUtils.nanoTime() - lastAsteroidTime > 1000000000) spawnRainAsteroids();

        // move the raindrops, remove any that are beneath the bottom edge of
        // the screen or that hit the bucket. In the latter case we play back
        // a sound effect as well.
        for (Iterator<Rectangle> iter = rainAsteroids.iterator(); iter.hasNext(); ) {
            Rectangle raindrop = iter.next();
            raindrop.y -= 200 * Gdx.graphics.getDeltaTime();
            if(raindrop.y + 64 < 0) iter.remove();
            if(raindrop.overlaps(rocket)) {
                //dropSound.play();
                iter.remove();
            }
        }
    }

    @Override
    public void dispose() {
        // dispose of all the native resources
        rocketImage.dispose();
        asteroidImage.dispose();
        spaceImage.dispose();
        //dropSound.dispose();
        //rainMusic.dispose();
        batch.dispose();
        spriteBatch.dispose();
    }
}
