package es.eukariotas.giame.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.snackbar.Snackbar
import es.eukariotas.giame.databinding.FragmentLoginBinding
import es.eukariotas.giame.persistence.data.domain.UserUseCase
import es.eukariotas.giame.ui.viewmodel.UserViewModel

class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btLogin.setOnClickListener {
            val usuario = binding.etUserName.text.toString()
            val password = binding.etPass.text.toString()
            Snackbar.make(view, "logueando el usuario: ${usuario} pass:${password}", Snackbar.LENGTH_LONG).show()
            try {
                var userViewModel = UserViewModel(UserUseCase(), requireContext())
                userViewModel.onCreate(usuario, password)
            }catch (e: Exception){
                Snackbar.make(view, "Error: ${e.message}", Snackbar.LENGTH_LONG).show()
            }

        }
        binding.btSingIn.setOnClickListener {
            try {
                val h = ping("192.168.5.182")
                Snackbar.make(view, "Ping: ${h}", Snackbar.LENGTH_LONG).show()
            }catch (e: Exception){
                Snackbar.make(view, "Error: ${e.message}", Snackbar.LENGTH_LONG).show()
            }
        }
    }
    fun ping(ip: String): Boolean {
        try {
            val p = ProcessBuilder("ping", "-c", "1", ip).start()
            return p.waitFor() == 0
        } catch (e: Exception) {
            return false
        }
    }

}