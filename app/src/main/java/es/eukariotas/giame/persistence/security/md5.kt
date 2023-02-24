package es.eukariotas.giame.persistence.security

import java.security.MessageDigest

class md5 {

    companion object{
        @Throws(Exception::class)
        fun encrypt(data: String): String {
            val md5 = MessageDigest.getInstance("MD5")
            md5.update(data.toByte())
            return md5.digest().toString()
        }
    }
}