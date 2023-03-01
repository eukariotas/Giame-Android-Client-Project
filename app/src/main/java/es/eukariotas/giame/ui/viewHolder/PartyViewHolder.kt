package es.eukariotas.giame.ui.viewHolder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import es.eukariotas.giame.core.RetrofitHelper
import es.eukariotas.giame.databinding.ItemPartidaBinding
import es.eukariotas.giame.persistence.data.apiclient.PartyApiClient
import es.eukariotas.giame.persistence.data.model.PartyModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PartyViewHolder(val binding: ItemPartidaBinding):
    RecyclerView.ViewHolder(binding.root) {
    init {
        binding.root.setOnClickListener {
           CoroutineScope(Dispatchers.IO).launch {
               val call = RetrofitHelper.getRetrofit().create(PartyApiClient::class.java).joinParty(binding.tvNombreCreador.text.toString().toInt())

           }
        }
    }
}