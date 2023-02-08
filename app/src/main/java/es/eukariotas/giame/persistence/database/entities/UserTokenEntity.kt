package es.eukariotas.giame.persistence.database.entities

import androidx.room.Embedded
import androidx.room.Relation


data class UserTokenEntity(
    @Embedded val user: UserEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "user_id"
    )
    val token: TokenEntity
)