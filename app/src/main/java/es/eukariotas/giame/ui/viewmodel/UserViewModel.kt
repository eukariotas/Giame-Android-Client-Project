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
    val userModel = MutableLiveData<UserModel?>()
    fun onCreate(user : String, password: String) = viewModelScope.launch {
        viewModelScope.launch {
            val result = userUseCase(user, password, context)
            if (result != null) {
                println("result: ${result}")
                userModel.postValue(result)
            } else {
                println("Usuario no encontrado")
            }
        }
    }
}