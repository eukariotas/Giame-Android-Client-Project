package es.eukariotas.giame.persistence.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class TurnModel(
    @SerializedName("id") val id: Int,
    @SerializedName("info") val info: String,
    @SerializedName("party_id") val party: PartyModel,
    @SerializedName("user_id") val user: UserModel,
    @SerializedName("end") val end: Boolean,
    @SerializedName("next_turn_id") val nextTurn: TurnModel,
    @SerializedName("num_turn") val num_turn: Int
) :Parcelable {

    /**
     * Funcion para obtener el turno mediante el id_party y la suma del numero del turno
     */
    companion object {
        fun obtenerTurno(party_id: Int, num_turn: Int): Int {
            return party_id + num_turn
        }
    }

    constructor(info: String, party_id: PartyModel, user_id: UserModel, end: Boolean, nextTurn: TurnModel, num_turn: Int)
            : this( obtenerTurno(party_id.id, num_turn), info, party_id, user_id, end, nextTurn, num_turn)
}

