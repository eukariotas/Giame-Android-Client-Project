package es.eukariotas.giame.persistence.data.apiclient

import es.eukariotas.giame.persistence.data.model.UserModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface UserApiClient {
    /**
     * Funcioner de prueba
     */
    @GET("/user/verify/{name}/{password}")
    suspend fun getUser(@Query("name") name: String,@Query("password") password: String): Response<UserModel>

    /**
     * Función que se usará para el login
     */
    @GET("/user/login/{name}/{password}")
    suspend fun login(@Query("name") name: String,@Query("password") password: String): Response<UserModel>

    /**
     * Función que se usará para el registro
     */
    @GET("/user/register/{name}/{password}/{email}")
    suspend fun register(@Query("name") name: String,@Query("password") password: String, @Query("email") email: String): Response<UserModel>


}