package es.eukariotas.giame.ui.viewHolder

import android.content.Context
import android.content.Intent
import android.provider.ContactsContract.RawContacts.Data
import android.view.View
import androidx.core.content.ContextCompat.startActivity
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import es.eukariotas.giame.ConexionFragment
import es.eukariotas.giame.R
import es.eukariotas.giame.core.RetrofitHelper
import es.eukariotas.giame.databinding.ItemPartidaBinding
import es.eukariotas.giame.game.Aviones.AvionesLauncher
import es.eukariotas.giame.game.ajedrez.AjedrezLauncher
import es.eukariotas.giame.persistence.DataBaseProv
import es.eukariotas.giame.persistence.data.apiclient.PartyApiClient
import es.eukariotas.giame.persistence.data.model.PartyModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PartyViewHolder(val binding: ItemPartidaBinding):
    RecyclerView.ViewHolder(binding.root) {
    init {
        binding.root.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                    val call =RetrofitHelper.getRetrofit().create(PartyApiClient::class.java).joinParty(binding.tvNombreCreador.text.toString().toInt())
                    if (call.isSuccessful) {
                        val partyModel = call.body()!!
                        if (partyModel != null){
                            CoroutineScope(Dispatchers.Main).launch {
                                DataBaseProv.partidaActual = partyModel
                                DataBaseProv.playerNum = 1
                                when(ConexionFragment.juego){
                                    "ajedrez" -> startActivity(binding.root.context, Intent(binding.root.context, AjedrezLauncher::class.java), null)
                                    "aviones" -> startActivity(binding.root.context, Intent(binding.root.context, AvionesLauncher::class.java), null)
                                    "enraya" -> binding.root.findNavController().navigate(R.id.action_ConexionFragment_to_SecondFragment)
                                }

                            }
                        } else {
                            CoroutineScope(Dispatchers.Main).launch {
                                Snackbar.make(
                                    binding.root,
                                    "No se ha podido unir a la partida",
                                    Snackbar.LENGTH_SHORT
                                ).show()
                            }

                        }
                    }
            }
        }
    }

}