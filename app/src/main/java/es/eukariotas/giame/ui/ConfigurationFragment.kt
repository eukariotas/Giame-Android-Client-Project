package es.eukariotas.giame.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import es.eukariotas.giame.R
import es.eukariotas.giame.databinding.FragmentConfigurationBinding
import es.eukariotas.giame.databinding.FragmentLoginBinding
import java.util.*


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
            cambiarIdioma()
            serverIp = binding.etIpServer.text.toString()
            serverPort = binding.etPuerto.text.toString()
            findNavController().navigate(R.id.action_ConfigFragment_to_MenuFragment)
            Toast.makeText(context, "Configuración guardada", Toast.LENGTH_SHORT).show()
            //volver a inicio
        }
        binding.btCancelar.setOnClickListener {
            requireActivity().onBackPressed();
        }
        //añado un adapter con los idiomas al spinner
        val adapter = ArrayAdapter.createFromResource(
            requireContext(),
            R.array.idiomas,
            android.R.layout.simple_spinner_item
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spIdioma.adapter = adapter

    }


    companion object {
    //leer estos datos de la base de datos
    //tambien guardarlos al cambiar
        var serverIp = "192.168.5.182"
        var serverPort = "8081"
    }
    fun cambiarIdioma() {
        val selectedLanguage = when(binding.spIdioma.selectedItemPosition){
            1 -> "es"
            0 -> "en"
            2 -> "fr"
            3 -> "zh"
            else -> "en"
        }
        val locale = Locale(selectedLanguage)
        val configuration = resources.configuration
        configuration.setLocale(locale)
        resources.updateConfiguration(configuration, resources.displayMetrics)
    }
}