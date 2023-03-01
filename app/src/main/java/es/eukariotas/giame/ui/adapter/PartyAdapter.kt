package es.eukariotas.giame.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import es.eukariotas.giame.R
import es.eukariotas.giame.databinding.ItemPartidaBinding
import es.eukariotas.giame.persistence.data.model.PartyModel
import es.eukariotas.giame.ui.viewHolder.PartyViewHolder

class PartyAdapter(val parties: List<PartyModel>):RecyclerView.Adapter<PartyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PartyViewHolder {
        val binding = ItemPartidaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PartyViewHolder(binding)
    }

    override fun getItemCount(): Int = parties.size

    override fun onBindViewHolder(holder: PartyViewHolder, position: Int) {
        with(holder){
            val item = parties[position]
            binding.tvTipoJuego.text = item.tipeGame
            binding.tvNombreCreador.text = item.id.toString()
        }

    }



}
