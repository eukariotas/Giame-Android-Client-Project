package es.eukariotas.giame.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import es.eukariotas.giame.R
import es.eukariotas.giame.core.RetrofitHelper
import es.eukariotas.giame.databinding.FragmentLoginBinding
import es.eukariotas.giame.persistence.DataBaseProv
import es.eukariotas.giame.persistence.data.apiclient.UserApiClient
import es.eukariotas.giame.persistence.security.md5
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

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
                login(usuario, password)
            }catch (e:Exception){
                Snackbar.make(view, e.message.toString(), Snackbar.LENGTH_LONG).show()
            }
        }
        binding.btSingIn.setOnClickListener {
           findNavController().navigate(R.id.action_LoginFragment_to_RegisterFragment)
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

    fun login(user: String, password:String){
        CoroutineScope(Dispatchers.IO).launch {
            val call = RetrofitHelper.getRetrofit().create(UserApiClient::class.java).login(user, password)
            val response = call.body()//este es el usuario
            val header = call.headers()//este es el token
            if(call.isSuccessful){
                if (response?.user != null&& response?.id != null&& response?.email != null &&response?.password != null){
                    Snackbar.make(binding.root, "Usuario: ${response!!.user} logueado correctamente", Snackbar.LENGTH_LONG).show()
                    DataBaseProv.usuario= response
                    CoroutineScope(Dispatchers.Main).launch {
                        findNavController().navigate(R.id.action_LoginFragment_to_FirstFragment)

                    }
                }else {
                    Snackbar.make(binding.root, "Error al loguear", Snackbar.LENGTH_LONG).show()
                }
            }else{
                Snackbar.make(binding.root, "Error al loguear", Snackbar.LENGTH_LONG).show()
            }
        }
        if (DataBaseProv.usuario.user != "invitado"){
        }
    }

}