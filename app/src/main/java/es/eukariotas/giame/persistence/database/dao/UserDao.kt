package es.eukariotas.giame.persistence.database.dao

import androidx.room.Dao
import androidx.room.Query
import es.eukariotas.giame.persistence.database.entities.UserEntity

@Dao
interface UserDao {

    @Query("insert into user (id, user, password, email, image, country, description, last_login) values (:id, :name, :password, :email, :avatar, :country, :city, :lastLogin)")
    fun insertUser(id: Int, name: String, password: String, email: String, avatar: String, country: String, city: String, lastLogin: String)

    @Query("select * from user where id = :id")
    fun getUser(id: Int): UserEntity

    @Query("select * from user where user = :name and password = :password")
    fun getUser(name: String, password: String): UserEntity

    @Query("select token from token where user_id = :id")
    fun getToken(id: Int): String

    @Query("insert into token (token, user_id) values (:token, :id)")
    fun insertToken(token: String, id: Int)


    // TODO: metodo para verificar usuario
    //TODO: metodo para insertar usuario
    //TODO: metodo para actualizar usuario
    //TODO: metodo para consultar usuario


    //TODO: metodo para verificar token
    //TODO: metodo para manejar token
}