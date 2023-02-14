package es.eukariotas.giame.ui.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import es.eukariotas.giame.core.RetrofitHelper
import es.eukariotas.giame.persistence.data.apiclient.UserApiClient
import es.eukariotas.giame.persistence.data.domain.UserUseCase
import es.eukariotas.giame.persistence.data.model.UserModel
import es.eukariotas.giame.persistence.database.entities.UserEntity
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import javax.inject.Inject

class UserViewModel (): ViewModel() {
    val userModel = MutableLiveData<UserModel>()
    val token = MutableLiveData<String>()


    fun login(user: String, password:String) = viewModelScope.launch {
        val call = RetrofitHelper.getRetrofit().create(UserApiClient::class.java).login(user, password)
        if (call.isSuccessful){
            val response = call.body()//este es el usuario
            val header = call.headers()//este es el token
            if (response?.user != null&& response?.id != null&& response?.email != null &&response?.password != null){
                userModel.value = response!!
                token.value = header.get("token")
            }else {
                throw Exception("Error al loguear")
            }

        }
    }

}