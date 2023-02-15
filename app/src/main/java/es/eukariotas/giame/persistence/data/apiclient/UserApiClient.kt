package es.eukariotas.giame.persistence.data.apiclient

import es.eukariotas.giame.persistence.data.model.UserModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface UserApiClient {

    /**
     * Función que se usará para el login
     */
    @GET("/user/login/{name}/{password}")
    suspend fun login(@Path("name") name: String, @Path("password") password: String): Response<UserModel>

    /**
     * Función que se usará para el registro
     */
    @GET("/user/register/{name}/{password}/{email}")
    suspend fun register(@Path("name") name: String,@Path("password") password: String, @Path("email") email: String): Response<UserModel>


}