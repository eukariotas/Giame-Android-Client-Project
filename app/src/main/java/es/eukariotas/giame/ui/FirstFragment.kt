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
import es.eukariotas.giame.R
import es.eukariotas.giame.databinding.FragmentFirstBinding
import es.eukariotas.giame.game.Aviones.AvionesLauncher
import es.eukariotas.giame.game.ajedrez.AjedrezLauncher

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
            startActivity(Intent(activity, AvionesLauncher::class.java))
        }
        binding.btAjedrez.setOnClickListener {
            val intent = Intent( activity, AjedrezLauncher::class.java)
            startActivity(intent)
        }
        binding.btToLogin.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_LoginFragment)
        }
        binding.bt3enRaya.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}