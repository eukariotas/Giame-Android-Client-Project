package es.eukariotas.giame.persistence.data.apiclient

import es.eukariotas.giame.persistence.data.model.UserModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface UserApiClient {
    @GET("/user/verify/{name}/{password}")
    suspend fun getUser(@Query("name") name: String,@Query("password") password: String): Response<UserModel>


}