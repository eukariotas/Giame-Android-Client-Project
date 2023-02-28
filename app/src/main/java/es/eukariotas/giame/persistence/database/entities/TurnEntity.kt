package es.eukariotas.giame.persistence.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "turn")
data class TurnEntity (
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "info") val info: String,
    @ColumnInfo(name = "party_id") val party_id: PartyEntity,
    @ColumnInfo(name = "user_id") val user_id: UserEntity,
    @ColumnInfo(name = "end") val end: String,
    @ColumnInfo(name = "next_turn_id") val next_turn_id: TurnEntity,
    @ColumnInfo(name = "next_turn") val next_turn: String
    ){
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as TurnEntity

        if (id != other.id) return false
        if (info != other.info) return false
        if (party_id != other.party_id) return false
        if (user_id != other.user_id) return false
        if (end != other.end) return false
        if (next_turn_id != other.next_turn_id) return false
        if (next_turn != other.next_turn) return false

        return true
    }

}