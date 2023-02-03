package es.eukariotas.giame.ui

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import es.eukariotas.giame.databinding.FragmentFirstBinding
import es.eukariotas.giame.game.Aviones.AndroidLauncher
import es.eukariotas.giame.game.ajedrez.AjedrezController

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root



    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btAjedrez.setOnClickListener {
            val ajedrezController = AjedrezController()
            ajedrezController.mostrarTablero();
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}