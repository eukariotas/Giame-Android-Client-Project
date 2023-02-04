package es.eukariotas.giame.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration
import com.badlogic.gdx.backends.android.AndroidFragmentApplication
import com.badlogic.gdx.backends.android.AndroidGraphics
import es.eukariotas.giame.R
import es.eukariotas.giame.game.ajedrez.AjedrezController
import es.eukariotas.giame.game.ajedrez.AjedrezLauncher


class AjedrezFragment : Fragment() {

    private lateinit var ajedrezLauncher: AjedrezLauncher
    private lateinit var ajedrezController: AjedrezController
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        ajedrezController = AjedrezController()
        var config = AndroidApplicationConfiguration()
        return ajedrezLauncher.initializeForView(ajedrezController, config)
    }


}