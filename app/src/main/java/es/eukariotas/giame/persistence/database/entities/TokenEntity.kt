package es.eukariotas.giame.persistence.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "token")
data class TokenEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "token") val token: String,
    @ColumnInfo(name = "user_id") val userId: Int,
)
