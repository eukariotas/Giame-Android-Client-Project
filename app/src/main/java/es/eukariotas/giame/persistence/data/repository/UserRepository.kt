package es.eukariotas.giame.persistence.data.repository

import es.eukariotas.giame.persistence.data.apiService.UserService
import es.eukariotas.giame.persistence.database.dao.UserDao
import es.eukariotas.giame.persistence.database.entities.UserEntity
import java.security.MessageDigest
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