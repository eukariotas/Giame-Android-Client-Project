package es.eukariotas.giame.persistence.security

import android.os.Build
import androidx.annotation.RequiresApi
import es.eukariotas.giame.persistence.database.entities.UserEntity
import java.security.MessageDigest
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class md5 {

    companion object{
        @Throws(Exception::class)
        fun encrypt(data: String): String {
            val md5 = MessageDigest.getInstance("MD5")
            md5.update(data.toByte())
            return md5.digest().toString()
        }

        @RequiresApi(Build.VERSION_CODES.O)
        fun createToken(id: Int, nombreUsuario: String): String {
            // Obtenemos la fecha actual
            val fechaActual = LocalDateTime.now()
            // Sumamos 7 días a la fecha actual
            val fechaExpiracion = fechaActual.plusDays(7)
            // Formateamos la fecha de expiración a una cadena en formato ISO
            val formatter = DateTimeFormatter.ISO_DATE_TIME
            val fechaExpiracionStr = fechaExpiracion.format(formatter)
            // Creamos el token concatenando los valores del id, la fecha de expiración y el nombre de usuario
            val token = "$id-$fechaExpiracionStr-$nombreUsuario"
            return token
        }


    }
}

