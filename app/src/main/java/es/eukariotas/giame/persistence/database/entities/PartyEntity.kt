package es.eukariotas.giame.persistence.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Timestamp

@Entity(tableName = "party")
data class PartyEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "winner") val winner: Int,
    @ColumnInfo(name = "date") val date: Timestamp,
    @ColumnInfo(name = "max_player") val max_player: Int,
    @ColumnInfo(name = "tipe_game") val tipe_game: String,
    @ColumnInfo(name = "state") val state: String
    ){
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as PartyEntity

        if (id != other.id) return false
        if (winner != other.winner) return false
        if (date != other.date) return false
        if (max_player != other.max_player) return false
        if (tipe_game != other.tipe_game) return false
        if (state != other.state) return false

        return true
    }
}


