package es.eukariotas.giame.core

import es.eukariotas.giame.persistence.DataBaseProv
import okhttp3.Interceptor
import okhttp3.Response

/**
 * Clase que se encarga de añadir los headers a las peticiones
 * TODO: leer los headers de la base de datos
 *
 */
class HeaderInterceptor:Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader("user", DataBaseProv.usuario.id.toString())
            .addHeader("token", DataBaseProv.token)
            .build()
        return chain.proceed(request)
    }
}