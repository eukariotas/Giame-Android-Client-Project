package es.eukariotas.giame.persistence.data.repository

import android.app.Application
import android.content.Context
import es.eukariotas.giame.persistence.data.apiService.UserService
import es.eukariotas.giame.persistence.data.apiclient.UserApiClient
import es.eukariotas.giame.persistence.data.model.UserModel
import es.eukariotas.giame.persistence.database.UserDatabase
import es.eukariotas.giame.persistence.database.dao.UserDao
import es.eukariotas.giame.persistence.database.entities.UserEntity
import javax.inject.Inject

class UserRepository @Inject constructor(
         private val api:UserService,
         private val userDao: UserDao
) {

    suspend fun login(name: String, password: String):UserEntity{
        return api.login(name, password)

    }

    suspend fun insertUser(repose: UserEntity){
        userDao.insertUser(repose.id, repose.name, repose.password, repose.email, repose.avatar?:"null", repose.country?:"null", repose.description?:"null", repose.lastLogin, repose.token)
    }
}