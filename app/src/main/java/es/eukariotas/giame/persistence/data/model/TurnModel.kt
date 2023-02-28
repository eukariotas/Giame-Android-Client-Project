package es.eukariotas.giame.persistence.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class TurnModel(
    @SerializedName("id") val id: Int,
    @SerializedName("informacion") val informacion: String,
    @SerializedName("num_turn") val numTurn: Int,
    @SerializedName("end") val end: Boolean,
    @SerializedName("party") val party: PartyModel
) : Parcelable

