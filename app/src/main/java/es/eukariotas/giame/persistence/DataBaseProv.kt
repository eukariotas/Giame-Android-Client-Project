package es.eukariotas.giame.persistence

import es.eukariotas.giame.persistence.data.model.PartyModel
import es.eukariotas.giame.persistence.data.model.UserModel

class DataBaseProv {
    companion object{
        var partidaActual: PartyModel? = null
        var playerNum : Int = 0
        var usuario = UserModel(99999999,"invitado","invitado@gmail.com","invitado","","cuenta de invitado","Es","")
        var token = ""

        fun updateToken(token:String){
            this.token = token
        }
        fun updateUsuario(usuarioRec:UserModel){
            if(usuarioRec.id != null){
                this.usuario = usuarioRec
            }

        }
    }
}