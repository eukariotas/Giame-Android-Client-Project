package es.eukariotas.giame

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import es.eukariotas.giame.core.RetrofitHelper
import es.eukariotas.giame.databinding.FragmentRegisterBinding
import es.eukariotas.giame.persistence.DataBaseProv
import es.eukariotas.giame.persistence.data.apiclient.UserApiClient
import es.eukariotas.giame.persistence.security.md5
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class RegisterFragment : Fragment() {
    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btRegister.setOnClickListener {
            register()
        }
        binding.btClearRegister.setOnClickListener {
            binding.etUserNameRegister.text.clear()
            binding.etPassRegister.text.clear()
            binding.etEmailRegister.text.clear()
        }
    }

    fun register(){
        val user = binding.etUserNameRegister.text.toString()
        val pass = md5.encrypt(binding.etPassRegister.text.toString())
        val email = binding.etEmailRegister.text.toString()
        CoroutineScope(Dispatchers.IO).launch {
            val call = RetrofitHelper.getRetrofit().create(UserApiClient::class.java).register(user, pass, email)
            if (call.isSuccessful){
                val response = call.body()
                val header = call.headers()
                if (response != null){
                    Snackbar.make(binding.root, "Error al loguear", Snackbar.LENGTH_LONG).show()
                    CoroutineScope(Dispatchers.Main).launch {
                        findNavController().navigate(R.id.action_RegisterFragment_to_FirstFragment)
                    }
                    DataBaseProv.token = header["token"].toString()
                    DataBaseProv.usuario = response
                }else{
                    Snackbar.make(binding.root, "Error al loguear", Snackbar.LENGTH_LONG).show()
                }
            }else{
                Snackbar.make(binding.root, "Error al loguear", Snackbar.LENGTH_LONG).show()
            }
        }
    }

}