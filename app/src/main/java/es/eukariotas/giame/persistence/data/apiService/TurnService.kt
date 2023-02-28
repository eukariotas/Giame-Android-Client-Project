package es.eukariotas.giame.persistence.data.apiService

import es.eukariotas.giame.persistence.data.apiclient.TurnApiClient
import javax.inject.Inject

class TurnService@Inject constructor(
    private val api: TurnApiClient
) {
    suspend fun getTurn(idTurno: Int):

}