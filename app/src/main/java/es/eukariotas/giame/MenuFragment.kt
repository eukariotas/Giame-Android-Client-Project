package es.eukariotas.giame

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import androidx.navigation.ui.setupActionBarWithNavController
import es.eukariotas.giame.databinding.FragmentConfigurationBinding
import es.eukariotas.giame.databinding.FragmentMenuBinding

class MenuFragment : Fragment() {
    private var _binding: FragmentMenuBinding? = null
    private val binding get() = _binding!!
    private lateinit var appBarConfiguration: AppBarConfiguration
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentMenuBinding.inflate(inflater, container, false)


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btInvitado.setOnClickListener {
            findNavController().navigate(R.id.action_MenuFragment_to_FirstFragment2)
        }
        binding.btIniciarSesion.setOnClickListener {
            findNavController().navigate(R.id.action_MenuFragment_to_LoginFragment)
        }

        //set on click listener del menu de toolbar

    }

}