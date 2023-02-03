package es.eukariotas.giame.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.badlogic.gdx.backends.android.AndroidApplication
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration
import es.eukariotas.giame.R
import es.eukariotas.giame.game.ajedrez.AjedrezController
import es.eukariotas.giame.game.ajedrez.AjedrezLauncher


class AjedrezFragment : Fragment() {
    val ajedrezController = AjedrezController()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val config = AndroidApplicationConfiguration()
        ajedrezController.create()
        return initializeForView(ajedrezController, config)

    }



}