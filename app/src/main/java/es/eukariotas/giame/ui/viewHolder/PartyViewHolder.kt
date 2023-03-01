package es.eukariotas.giame.ui.viewHolder

import android.content.Context
import android.content.Intent
import android.view.View
import androidx.core.content.ContextCompat.startActivity
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import es.eukariotas.giame.ConexionFragment
import es.eukariotas.giame.R
import es.eukariotas.giame.core.RetrofitHelper
import es.eukariotas.giame.databinding.ItemPartidaBinding
import es.eukariotas.giame.game.Aviones.AvionesLauncher
import es.eukariotas.giame.game.ajedrez.AjedrezLauncher
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
            CoroutineScope(Dispatchers.Main).launch {
                try {
                    val call = withContext(Dispatchers.IO) {
                        RetrofitHelper.getRetrofit()
                            .create(PartyApiClient::class.java)
                            .joinParty(binding.tvNombreCreador.text.toString().toInt())
                    }
                    if (call.isSuccessful) {
                        val partyModel = call.body()!!
                        val activity = binding.root.context as? ConexionFragment
                        CoroutineScope(Dispatchers.Main).launch {
                            startActivity(
                                    Intent(
                                        activity,
                                        AjedrezLauncher::class.java
                                    )
                                )
                        }
                        Snackbar.make(
                            binding.root,
                            "Te has unido a la partida",
                            Snackbar.LENGTH_SHORT
                        ).show()
                    } else {
                        Snackbar.make(
                            binding.root,
                            "Error al unirte a la partida",
                            Snackbar.LENGTH_SHORT
                        ).show()
                    }
                } catch (e: Exception) {
                    Snackbar.make(
                        binding.root,
                        "Error al unirte a la partida",
                        Snackbar.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }


    private fun startActivity(intent: Context) {
        TODO("Not yet implemented")
    }

    private fun Intent(activity: ConexionFragment?, java: Class<AjedrezLauncher>): Context {
        return Intent(activity, AjedrezLauncher::class.java)
    }
}