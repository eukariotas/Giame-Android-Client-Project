package es.eukariotas.giame.persistence.data.repository

import android.app.Application
import android.content.Context
import es.eukariotas.giame.persistence.data.apiService.UserService
import es.eukariotas.giame.persistence.data.apiclient.UserApiClient
import es.eukariotas.giame.persistence.data.model.UserModel
import es.eukariotas.giame.persistence.database.UserDatabase
import es.eukariotas.giame.persistence.database.dao.UserDao
import es.eukariotas.giame.persistence.database.entities.UserEntity

class UserRepository {
    private val api = UserService()
    private lateinit var application: Application
    private lateinit var db :UserDatabase


    operator fun invoke(context: Context){
        this.application= context.applicationContext as Application
        db = UserDatabase.getInstance(application)

    }

    suspend fun getUser(name: String, password: String, context: Context):UserModel{
        this.invoke(context)
        val repose = api.getUser(name, password)
        //TODO hacer que se guarde en la base de datos pero en el liveData
        //db.userDao().insertUser(repose.id, repose.user, repose.password, repose.email, repose.image, repose.country, repose.description, repose.lastLogin, repose.token)
        return repose
    }
}