package es.eukariotas.giame.ui.viewHolder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import es.eukariotas.giame.databinding.ItemPartidaBinding
import es.eukariotas.giame.persistence.data.model.PartyModel

class PartyViewHolder(view: View): RecyclerView.ViewHolder(view) {
    private val binding = ItemPartidaBinding.bind(view)
    fun bind(item: PartyModel) {
        binding.tvNombreCreador.text = item.status
        binding.tvTipoJuego.text = item.tipeGame

    }
}