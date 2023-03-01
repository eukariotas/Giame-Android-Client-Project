package es.eukariotas.giame.persistence.data.apiclient

import es.eukariotas.giame.persistence.data.model.TurnModel
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface TurnApiClient {

    /**
     * Función que se usará para obtener un turno
     */
    @GET("turno/{idTurno}")
    suspend fun getTurn(@Path("idTurno") idTurno: Int): Response<TurnModel>

    /**
     * Función que se usará para guardar un turno
     */
    @POST("turno/save")
    open fun saveTurno(@Body turno: TurnModel): Call<Response<Boolean>>
}