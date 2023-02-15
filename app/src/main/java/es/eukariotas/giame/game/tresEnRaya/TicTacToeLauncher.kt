package es.eukariotas.giame.game.tresEnRaya

import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration
import es.eukariotas.giame.game.ajedrez.AjedrezController
import org.lwjgl.Sys.initialize

class TicTacToeLauncher {
    fun main() {
          var config = AndroidApplicationConfiguration()
          initialize(TicTacToeController(), config)
     }
}