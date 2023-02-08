package es.eukariotas.giame.persistence.database

import androidx.room.Database
import androidx.room.RoomDatabase
import es.eukariotas.giame.persistence.database.dao.TokenDao
import es.eukariotas.giame.persistence.database.dao.UserDao
import es.eukariotas.giame.persistence.database.entities.TokenEntity
import es.eukariotas.giame.persistence.database.entities.UserEntity

@Database(entities = [UserEntity::class, TokenEntity::class], version = 1)
abstract class UserDatabase: RoomDatabase(){
    abstract fun userDao(): UserDao
    abstract fun tokenDao(): TokenDao
}