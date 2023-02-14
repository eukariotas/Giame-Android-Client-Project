package es.eukariotas.giame.persistence.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import es.eukariotas.giame.persistence.database.UserDatabase
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserModel(
    @SerializedName("id") val id: Int,
    @SerializedName("userName") val user:String,
    @SerializedName("userEmail") val email:String,
    @SerializedName("password") val password:String,
    @SerializedName("userImage") val image:String,
    @SerializedName("userDescription") val description:String,
    @SerializedName("userCountry") val country:String,
    @SerializedName("lastLogin") val lastLogin:String,
):Parcelable{

}
