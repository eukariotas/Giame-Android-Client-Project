package es.eukariotas.giame.persistence.data.domain

import android.content.Context
import es.eukariotas.giame.persistence.data.model.UserModel
import es.eukariotas.giame.persistence.data.repository.UserRepository
import es.eukariotas.giame.persistence.database.entities.UserEntity
import javax.inject.Inject

class UserUseCase @Inject constructor(
    private val userRepository: UserRepository
)
{ suspend operator fun invoke(name: String, password: String): UserEntity{
        val user = userRepository.login(name, password)
    userRepository.insertUser(user)
        return user
    }

}