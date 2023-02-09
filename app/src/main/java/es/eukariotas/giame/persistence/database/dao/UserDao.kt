package es.eukariotas.giame.persistence.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Update
import es.eukariotas.giame.persistence.database.entities.UserEntity

@Dao
interface UserDao {
    @Query("SELECT token FROM user")
    fun getToken(): String

    @Query("insert into user (id, user, password, email, image, country, description, last_login, token) values (:id, :user, :password, :email, :image, :country, :description, :last_login, :token)")
    fun insertUser(id: Int, user: String, password: String, email: String, image: String, country: String, description: String, last_login: String, token: String)

    @Query("select * from user where user = :name and password = :password")
    fun getUser(name: String, password: String): UserEntity

    @Update
    fun updateUser(user: UserEntity)


}