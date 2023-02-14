package es.eukariotas.giame.persistence.data.apiService

import es.eukariotas.giame.core.RetrofitHelper
import es.eukariotas.giame.persistence.data.apiclient.UserApiClient
import es.eukariotas.giame.persistence.data.model.UserModel
import es.eukariotas.giame.persistence.database.entities.UserEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UserService @Inject constructor(
    private val api: UserApiClient
) {

    suspend fun login(name:String, password:String): UserEntity {
        return withContext(Dispatchers.IO){
            val user = api.login(name, password)
            UserEntity(user.body()!!.id, user.body()!!.user, user.body()!!.password, user.body()!!.email, user.body()!!.image, user.body()!!.country, user.body()!!.description, user.body()!!.lastLogin, user.headers().get("token")!!)

        }
    }

}