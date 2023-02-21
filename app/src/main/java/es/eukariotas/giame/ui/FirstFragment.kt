package es.eukariotas.giame.ui

import android.content.Intent
import android.os.Bundle
import android.text.Layout
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import es.eukariotas.giame.ConexionFragment
import es.eukariotas.giame.R
import es.eukariotas.giame.databinding.FragmentFirstBinding
import es.eukariotas.giame.game.Aviones.AvionesLauncher
import es.eukariotas.giame.game.ajedrez.AjedrezLauncher
import es.eukariotas.giame.game.tresEnRaya.TicTacToeLauncher

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
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
        binding.btAvioncitos.setOnClickListener{
            ConexionFragment.juego = "aviones"
            findNavController().navigate(R.id.action_FirstFragment_to_ConexionFragment)
        }
        binding.btAjedrez.setOnClickListener {
            ConexionFragment.juego = "ajedrez"
            findNavController().navigate(R.id.action_FirstFragment_to_ConexionFragment)
        }
        binding.bt3enRaya.setOnClickListener {
            ConexionFragment.juego = "enraya"
            findNavController().navigate(R.id.action_FirstFragment_to_ConexionFragment)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}