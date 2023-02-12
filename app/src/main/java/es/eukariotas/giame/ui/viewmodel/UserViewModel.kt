package es.eukariotas.giame.ui.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.scopes.ViewModelScoped
import es.eukariotas.giame.persistence.data.domain.UserUseCase
import es.eukariotas.giame.persistence.data.model.UserModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class UserViewModel @Inject constructor(
    private val userUseCase: UserUseCase,
    private val context: Context
): ViewModel() {
    lateinit var userActivo:UserModel
    fun onCreate(user : String, password: String) = viewModelScope.launch {
        viewModelScope.launch {
            val result = userUseCase(user, password, context)
            if (result != null) {
                println("result: ${result}")
                println("usuario: ${result.user} logeado")
                //TODO hacer que se guarde en la base de datos pero en el liveData
                userActivo =result
            } else {
                println("Usuario no encontrado")
            }
        }
    }
}