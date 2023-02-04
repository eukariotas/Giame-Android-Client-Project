package es.eukariotas.giame.game.ajedrez

import com.badlogic.gdx.backends.android.AndroidApplication
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration

public class AjedrezLauncher: AndroidApplication() {
    override fun onCreate(savedInstanceState: android.os.Bundle?) {
        super.onCreate(savedInstanceState)
        var config = AndroidApplicationConfiguration()
        initialize(AjedrezController(), config)
    }
}