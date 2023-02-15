package es.eukariotas.giame.game.tresEnRaya

import com.badlogic.gdx.backends.android.AndroidApplication
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration
import es.eukariotas.giame.TicTacToeLogic
import es.eukariotas.giame.game.ajedrez.AjedrezController
import org.lwjgl.Sys.initialize

public class TicTacToeLauncher: AndroidApplication() {
    override fun onCreate(savedInstanceState: android.os.Bundle?) {
        super.onCreate(savedInstanceState)
        var config = AndroidApplicationConfiguration()
        initialize(TicTacToeLogic(), config)
    }

    private fun initialize(ticTacToeLogic: TicTacToeLogic, config: AndroidApplicationConfiguration) {

    }
}