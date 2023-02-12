package es.eukariotas.giame.persistence.data.apiService

import es.eukariotas.giame.core.RetrofitHelper
import es.eukariotas.giame.persistence.data.apiclient.UserApiClient
import es.eukariotas.giame.persistence.data.model.UserModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UserService @Inject constructor() {
    private val retrofit = RetrofitHelper.getRetrofit()

    suspend fun getUser(name: String, password: String): UserModel {
        return withContext(Dispatchers.IO){
            val response = retrofit.create(UserApiClient::class.java).getUser(name, password)
            response.body()?: UserModel(0, "invitado", "invitado", "", "", "", "", "")
        }
    }

    suspend fun login(name:String, password:String): UserModel {
        return withContext(Dispatchers.IO){
            val response = retrofit.create(UserApiClient::class.java).login(name, password)
            response.body()?: UserModel(0, "invitado", "invitado", "", "", "", "", "")

        }
    }

}