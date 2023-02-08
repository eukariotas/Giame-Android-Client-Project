package es.eukariotas.giame.persistence.data.model

import com.google.gson.annotations.SerializedName

data class UserModel(
    @SerializedName("id") val id: Int,
    @SerializedName("user") val user:String,
    @SerializedName("password") val password:String,
    @SerializedName("email") val email:String,
    @SerializedName("image") val image:String,
    @SerializedName("country") val country:String,
    @SerializedName("description") val description:String,
    @SerializedName("last_login") val lastLogin:String,
    @SerializedName("token") val token:String
)
