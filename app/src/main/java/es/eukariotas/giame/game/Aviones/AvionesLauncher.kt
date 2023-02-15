package es.eukariotas.giame.game.Aviones

import com.badlogic.gdx.backends.android.AndroidApplication
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration

class AvionesLauncher: AndroidApplication() {
    override fun onCreate(savedInstanceState: android.os.Bundle?) {
        super.onCreate(savedInstanceState)
        var config = AndroidApplicationConfiguration()
        config.useAccelerometer = false
        config.useCompass = false
        return initialize(AvionesController(), config)
    }
}