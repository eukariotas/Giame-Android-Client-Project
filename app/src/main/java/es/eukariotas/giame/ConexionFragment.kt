package es.eukariotas.giame

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import es.eukariotas.giame.core.RetrofitHelper
import es.eukariotas.giame.databinding.FragmentConexionBinding
import es.eukariotas.giame.game.Aviones.AvionesLauncher
import es.eukariotas.giame.game.ajedrez.AjedrezLauncher
import es.eukariotas.giame.game.tresEnRaya.TicTacToeLauncher
import es.eukariotas.giame.persistence.DataBaseProv
import es.eukariotas.giame.persistence.data.apiclient.PartyApiClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


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
            /*binding.btMaquina.visibility = View.INVISIBLE
            binding.btLocal.visibility = View.INVISIBLE
            binding.btOnline.visibility = View.INVISIBLE
            binding.btBuscarPartida .visibility = View.VISIBLE
            binding.btCrearPartida.visibility = View.VISIBLE*/
            Snackbar.make(view, "Este modo aun se encuentra en desarrollo", Snackbar.LENGTH_LONG).show()
        }
        binding.btOnline.setOnClickListener{
            if (DataBaseProv.usuario.user == "invitado"){
                Snackbar.make(view, "Debes iniciar sesión para jugar online", Snackbar.LENGTH_LONG).show()
            }else{
            tipoJuego = "online"

            binding.btMaquina.visibility = View.INVISIBLE
            binding.btLocal.visibility = View.INVISIBLE
            binding.btOnline.visibility = View.INVISIBLE
            binding.btBuscarPartida .visibility = View.VISIBLE
            binding.btCrearPartida.visibility = View.VISIBLE
            }
        }
        binding.btMaquina.setOnClickListener {
            tipoJuego = "maquina"
            when(juego){
                "ajedrez" -> startActivity(Intent(activity, AjedrezLauncher::class.java))
                "aviones" -> startActivity(Intent(activity, AvionesLauncher::class.java))
                "enraya" -> findNavController().navigate(R.id.action_ConexionFragment_to_SecondFragment)
            }
        }

        binding.btCrearPartida.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                val call = RetrofitHelper.getRetrofit().create(PartyApiClient::class.java).createParty(juego)
                val response = call.body()//esta es la partida
                val header = call.headers()
                if(call.isSuccessful){
                    if (response != null) {
                        DataBaseProv.playerNum = 1
                        DataBaseProv.partidaActual= response
                        CoroutineScope(Dispatchers.Main).launch {
                            Snackbar.make(view, "Partida creada ${response.id}, ${response.tipeGame}, ${response.status}", Snackbar.LENGTH_LONG).show()
                            when(juego){
                                "ajedrez" -> startActivity(Intent(activity, AjedrezLauncher::class.java))
                                "aviones" -> startActivity(Intent(activity, AvionesLauncher::class.java))
                                "enraya" -> findNavController().navigate(R.id.action_ConexionFragment_to_SecondFragment)
                            }
                        }
                    }else {
                        Snackbar.make(binding.root, "Error al loguear", Snackbar.LENGTH_LONG).show()
                    }
                }else{
                    Snackbar.make(binding.root, "Error al loguear", Snackbar.LENGTH_LONG).show()
                }
            }

        }

        binding.btBuscarPartida.setOnClickListener {
            findNavController().navigate(R.id.action_ConexionFragment_to_BuscarPartidaFragment)
        }



    }

    companion object{
        var tipoJuego = ""
        var juego = ""
    }

}