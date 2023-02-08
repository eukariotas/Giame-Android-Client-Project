package es.eukariotas.giame.persistence.data.repository

import android.app.Application
import android.content.Context
import es.eukariotas.giame.persistence.data.apiService.UserService
import es.eukariotas.giame.persistence.data.model.UserModel
import es.eukariotas.giame.persistence.database.UserDatabase
import es.eukariotas.giame.persistence.database.dao.UserDao
import es.eukariotas.giame.persistence.database.entities.UserEntity

class UserRepository {
    private val api = UserService()
    private lateinit var application: Application
    private lateinit var db :UserDatabase
    private lateinit var userDao: UserDao

    operator fun invoke(context: Context){
        this.application= context.applicationContext as Application
        db = UserDatabase.getInstance(application)
        userDao = db.userDao()
    }

    suspend fun getUser(name: String, password: String):UserModel{
        val repose = api.getUser(name, password)
        userDao.insertUser(repose.id, repose.user, repose.password, repose.email, repose.image, repose.country, repose.description, repose.lastLogin, repose.token)
        return repose
    }
}