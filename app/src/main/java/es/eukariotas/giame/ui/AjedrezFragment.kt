package es.eukariotas.giame.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration
import com.badlogic.gdx.backends.android.AndroidFragmentApplication
import com.badlogic.gdx.backends.android.AndroidGraphics
import es.eukariotas.giame.game.ajedrez.AjedrezController
import es.eukariotas.giame.game.ajedrez.AjedrezLauncher


class AjedrezFragment : Fragment(), AndroidFragmentApplication.Callbacks {

    private lateinit var ajedrezLauncher: AjedrezLauncher
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        ajedrezLauncher = AjedrezLauncher()
        return ajedrezLauncher.initializeForView(AjedrezController(), AndroidApplicationConfiguration())
    }

    override fun exit() {
        TODO("Not yet implemented")
    }
}