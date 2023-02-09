package es.eukariotas.giame.persistence.data.domain

import android.content.Context
import es.eukariotas.giame.persistence.data.model.UserModel
import es.eukariotas.giame.persistence.data.repository.UserRepository

class UserUseCase {
    private val repository = UserRepository()

    suspend operator fun invoke(name: String, password: String, context: Context): UserModel?= repository.getUser(name, password, context)
}