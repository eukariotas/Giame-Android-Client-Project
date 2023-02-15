package es.eukariotas.giame.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import es.eukariotas.giame.R
import es.eukariotas.giame.databinding.FragmentConfigurationBinding
import es.eukariotas.giame.databinding.FragmentLoginBinding


class ConfigurationFragment : Fragment() {

    private var _binding: FragmentConfigurationBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentConfigurationBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.etIpServer.setText(serverIp)
        binding.etPuerto.setText(serverPort)

        binding.btGuardar.setOnClickListener {
            serverIp = binding.etIpServer.text.toString()
            serverPort = binding.etPuerto.text.toString()
            findNavController().navigate(R.id.action_ConfigFragment_to_FirstFragment)
            Toast.makeText(context, "Configuración guardada", Toast.LENGTH_SHORT).show()
            //volver a inicio
        }
        binding.btCancelar.setOnClickListener {
            findNavController().navigate(R.id.action_ConfigFragment_to_FirstFragment)
        }
    }


    companion object {
    //leer estos datos de la base de datos
    //tambien guardarlos al cambiar
        var serverIp = "192.168.5.182"
        var serverPort = "8081"
    }
}