package es.eukariotas.giame

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import es.eukariotas.giame.databinding.FragmentConexionBinding
import es.eukariotas.giame.game.Aviones.AvionesLauncher
import es.eukariotas.giame.game.ajedrez.AjedrezLauncher
import es.eukariotas.giame.game.tresEnRaya.TicTacToeLauncher


class ConexionFragment : Fragment() {

    private var _binding: FragmentConexionBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentConexionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btLocal.setOnClickListener{
            tipoJuego = "local"
            binding.btMaquina.visibility = View.INVISIBLE
            binding.btLocal.visibility = View.INVISIBLE
            binding.btOnline.visibility = View.INVISIBLE
            binding.btBuscarPartida .visibility = View.VISIBLE
            binding.btCrearPartida.visibility = View.VISIBLE
        }
        binding.btOnline.setOnClickListener{
            tipoJuego = "online"
            binding.btMaquina.visibility = View.INVISIBLE
            binding.btLocal.visibility = View.INVISIBLE
            binding.btOnline.visibility = View.INVISIBLE
            binding.btBuscarPartida .visibility = View.VISIBLE
            binding.btCrearPartida.visibility = View.VISIBLE
        }
        binding.btMaquina.setOnClickListener {
            tipoJuego = "maquina"
            when(juego){
                "ajedrez" -> startActivity(Intent( activity, AjedrezLauncher::class.java))
                "aviones" -> startActivity(Intent(activity, AvionesLauncher::class.java))
                "enraya" -> findNavController().navigate(R.id.action_ConexionFragment_to_SecondFragment)
            }
        }



    }

    companion object{
        var tipoJuego = ""
        var juego = ""
    }

}