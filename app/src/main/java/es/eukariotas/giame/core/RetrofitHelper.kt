package es.eukariotas.giame.core

import es.eukariotas.giame.ui.ConfigurationFragment
import es.eukariotas.giame.ui.LoginFragment
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {
    //TODO: poner esta variable en un fichero de configuración
    val ipServer = ConfigurationFragment.serverIp
    val portServer = ConfigurationFragment.serverPort
    val urlServer = "http://${ipServer}:${portServer}/"
    fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(urlServer)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}