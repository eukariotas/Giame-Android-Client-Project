package es.eukariotas.giame.persistence.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class PartyModel(
    @SerializedName("id") val id: Int,
    @SerializedName("winner") val winner :Int,
    @SerializedName("date") val date :String,
    @SerializedName("max_player") val maxPlayer :Int,
    @SerializedName("tipe_game") val tipeGame :String,
    @SerializedName("state") val state :Boolean
) :Parcelable{}