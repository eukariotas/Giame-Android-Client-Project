package es.eukariotas.giame.persistence.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import java.sql.Timestamp

@Parcelize
data class PartyModel(
    @SerializedName("id") val id: Int,
    @SerializedName("ganador") val ganador :Int?,
    @SerializedName("comienzo_partida") val comienzoPartida :String,
    @SerializedName("max_players") val maxPlayers :Int,
    @SerializedName("tipe_game") val tipeGame :String,
    @SerializedName("status") val status :String,
) :Parcelable{}