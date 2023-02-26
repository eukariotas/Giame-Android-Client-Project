package es.eukariotas.giame.game.tresEnRaya

import androidx.fragment.app.Fragment
import com.badlogic.gdx.backends.android.AndroidApplication
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration
import es.eukariotas.giame.game.ajedrez.AjedrezController

public class TicTacToeLauncher: Fragment() {
    override fun onCreate(savedInstanceState: android.os.Bundle?) {
        super.onCreate(savedInstanceState)
        var config = AndroidApplicationConfiguration()
        initialize(TicTacToeLogic(), config)
    }
}

    private fun initialize(ticTacToeLogic: TicTacToeLogic, config: AndroidApplicationConfiguration) {

    }