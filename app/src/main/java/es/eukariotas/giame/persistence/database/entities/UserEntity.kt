package es.eukariotas.giame.persistence.database.entities

import androidx.room.*


@Entity(tableName = "user")
data class UserEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "user") val name: String,
    @ColumnInfo(name = "password") val password: String,
    @ColumnInfo(name = "email") val email: String,
    @ColumnInfo(name = "image") val avatar: String,
    @ColumnInfo(name = "Country") val country: String,
    @ColumnInfo(name = "description") val city: String,
    @ColumnInfo(name = "last_login") val lastLogin: String,
    @ColumnInfo(name = "token") val token: String
){
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as UserEntity

        if (id != other.id) return false
        if (name != other.name) return false
        if (email != other.email) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + name.hashCode()
        result = 31 * result + password.hashCode()
        result = 31 * result + email.hashCode()
        result = 31 * result + avatar.hashCode()
        result = 31 * result + country.hashCode()
        result = 31 * result + city.hashCode()
        result = 31 * result + lastLogin.hashCode()
        result = 31 * result + token.hashCode()
        return result
    }

}
