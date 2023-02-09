package es.eukariotas.giame.core

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {
    //TODO: poner esta variable en un fichero de configuración
    val ipServer = "127.0.0.1"
    val portServer = "8081"
    val urlServer = "http://192.168.5.182:8081/"
    fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(urlServer)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}