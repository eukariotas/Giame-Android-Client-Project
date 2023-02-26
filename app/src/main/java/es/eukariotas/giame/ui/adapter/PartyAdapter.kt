package es.eukariotas.giame.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import es.eukariotas.giame.R
import es.eukariotas.giame.persistence.data.model.PartyModel
import es.eukariotas.giame.ui.viewHolder.PartyViewHolder

class PartyAdapter(val parties: List<PartyModel>):RecyclerView.Adapter<PartyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PartyViewHolder {
        val layoutInflater: LayoutInflater = LayoutInflater.from(parent.context)
        return PartyViewHolder(layoutInflater.inflate(R.layout.item_partida, parent,false))
    }

    override fun getItemCount(): Int = parties.size

    override fun onBindViewHolder(holder: PartyViewHolder, position: Int) {
        val item = parties[position]
        holder.bind(item)
    }


}
