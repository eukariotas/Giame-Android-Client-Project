package es.eukariotas.giame.persistence.data.apiclient

import es.eukariotas.giame.persistence.data.model.UserModel
import retrofit2.Response
import retrofit2.http.GET

interface UserApiClient {
    @GET("/user/{name}/{password}")
    suspend fun getUser(name: String, password: String): Response<UserModel>


}