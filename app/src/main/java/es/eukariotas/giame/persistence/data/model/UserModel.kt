package es.eukariotas.giame.persistence.data.model

import com.google.gson.annotations.SerializedName

data class UserModel(
    @SerializedName("id") val id: Int,
    @SerializedName("userName") val user:String,
    @SerializedName("password") val password:String,
    @SerializedName("userEmail") val email:String,
    @SerializedName("userImage") val image:String,
    @SerializedName("userCountry") val country:String,
    @SerializedName("userDescription") val description:String,
    @SerializedName("lastLogin") val lastLogin:String,
    @SerializedName("token") val token:String
)
