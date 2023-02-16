package es.eukariotas.giame.persistence.data.model

import com.google.gson.annotations.SerializedName

data class TurnModel(
    @SerializedName("id") val id: Int,
    @SerializedName("info") val info:String,
    @SerializedName("party_id") val party:PartyModel,
    @SerializedName("user_id") val user:UserModel,
    @SerializedName("end") val end: Boolean,
    @SerializedName("next_turn_id") val nextTurn: TurnModel
)
