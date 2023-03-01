package es.eukariotas.giame

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import es.eukariotas.giame.core.RetrofitHelper
import es.eukariotas.giame.databinding.FragmentBuscarPartidaBinding
import es.eukariotas.giame.persistence.data.apiclient.PartyApiClient
import es.eukariotas.giame.persistence.data.apiclient.UserApiClient
import es.eukariotas.giame.persistence.data.model.PartyModel
import es.eukariotas.giame.ui.adapter.PartyAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class BuscarPartidaFragment : Fragment() {

    private var _binding: FragmentBuscarPartidaBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: PartyAdapter
    private val partyList = mutableListOf<PartyModel>()
    private var buscar = true

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBuscarPartidaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        buscarPartidas()
        iniciarRecyclerView()
        partidasDisponibles()



    }

    private fun buscarPartidas() {
        CoroutineScope(Dispatchers.IO).launch {
            while (buscar) {
                val call = RetrofitHelper.getRetrofit().create(PartyApiClient::class.java).getOpenParties()
                if (call.isSuccessful) {
                    val list = call.body()!!
                    if (list.size != partyList.size) {
                        partyList.clear()
                        partyList.addAll(list)
                        activity?.runOnUiThread {
                            adapter.notifyDataSetChanged()
                        }
                    }
                }else{
                    activity?.runOnUiThread {
                        Toast.makeText(context, "Error al buscar partidas", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    private fun iniciarRecyclerView() {
        adapter = PartyAdapter(partyList)
        binding.rvPartidasAbiertas.layoutManager = LinearLayoutManager(context)
        binding.rvPartidasAbiertas.adapter = adapter
    }

    //Metodo que actualice la lista de partidas disponibles cada x tiempo
    private fun actualizarListaPartidas() {

    }

    //RecicleView con las partidas disponibles
    private fun partidasDisponibles() {
        CoroutineScope(Dispatchers.IO).launch {
            val call = RetrofitHelper.getRetrofit().create(PartyApiClient::class.java).getPartiesByState()

            if (call.isSuccessful) {
                val list = call.body()!!
                val openParties = list.filter { it.status == "open" }
                if (openParties.size != partyList.size) {
                    partyList.clear()
                    partyList.addAll(openParties)
                    activity?.runOnUiThread {
                        adapter.notifyDataSetChanged()
                    }
                }
            } else {
                activity?.runOnUiThread {
                    Toast.makeText(context, "Error al buscar partidas", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }



    //Al pulsar una partida, se unira a ella (llamada retrofit)
    private fun unirseAPartida() {

    }

}
