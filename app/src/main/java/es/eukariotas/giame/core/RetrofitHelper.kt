package es.eukariotas.giame.core

import es.eukariotas.giame.ui.ConfigurationFragment
import es.eukariotas.giame.ui.LoginFragment
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {
    val ipServer = ConfigurationFragment.serverIp
    val portServer = ConfigurationFragment.serverPort
    val urlServer = "http://${ipServer}:${portServer}/"
    val httpClient = OkHttpClient.Builder().addInterceptor(HeaderInterceptor()).build()
    fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(urlServer)
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}